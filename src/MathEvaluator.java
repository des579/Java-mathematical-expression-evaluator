import java.util.ArrayList;
import java.lang.Character;

public class MathEvaluator {
    public double calculate(String expression) {
        Lexer lexer = new Lexer(expression);
        Evaluator evaluator = new Evaluator(lexer.tokens);
        return evaluator.result;
    }

}

class Evaluator {
    private ArrayList<Token> tokens = new ArrayList<Token>();
    private int posCurrent = 0;

    public double result = 0;

    Evaluator(ArrayList<Token> tokens) {
        this.tokens = tokens;

        this.result = this.expr();
    }

    private double expr() {
        return this.eval();
    }

    private double eval() {
        double left = this.factor();
        double result = left;
        while (this.match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = this.tokens.get(this.posCurrent-1);
            double right = this.factor();
            if (operator.type == TokenType.PLUS)
                result += right;
            else if (operator.type == TokenType.MINUS)
                result -= right;
        }
        return result;
    }

    private double factor() {
        double left = this.primary();
        double result = left;
        while (this.match(TokenType.STAR, TokenType.SLASH)) {
            Token operator = this.tokens.get(this.posCurrent-1);
            double right = this.primary();
            if (operator.type == TokenType.STAR)
                result *= right;
            else if (operator.type == TokenType.SLASH)
                result /= right;
        }

        return result;
    }

    private double primary() {
        if (this.match(TokenType.MINUS))
            return -1 * this.primary();
        else if (this.match(TokenType.LP)) {
            double n = this.expr();
            this.posCurrent++; // skip ')'
            return n;
        }
        return this.advance().literal;
    }

    private boolean match(TokenType... types) {
        Token t = this.peek();
        for (TokenType type : types) {
            if (t.type == type) {
                this.posCurrent++;
                return true;
            }
        }
        return false;
    }

    private Token peek() {
        return this.tokens.get(this.posCurrent);
    }

    private Token advance() {
        this.posCurrent++;
        return this.tokens.get(this.posCurrent-1);
    }
}

class Lexer {
    private String expression;

    private int posStart = 0;
    private int posCurrent = 0;

    public ArrayList<Token> tokens = new ArrayList<Token>();

    Lexer(String expression) {
        this.expression = expression;
        
        // start lexer
        this.process();
        this.addToken(0, TokenType.END);
    }

    private void process() {
        while (!this.isAtEnd()) {
            this.posStart = this.posCurrent;
            char ch = this.advance();
            this.processCh(ch);
        }
    }

    private void processCh(char ch) {
        switch (ch) {
            case '(':
                this.addToken(0, TokenType.LP);
                break;
            case ')':
                this.addToken(0, TokenType.RP);
                break;
            case '+':
                this.addToken(0, TokenType.PLUS);
                break;
            case '-':
                this.addToken(0, TokenType.MINUS);
                break;
            case '*':
                this.addToken(0, TokenType.STAR);
                break;
            case '/':
                this.addToken(0, TokenType.SLASH);
                break;
            default:
                if (Character.isDigit(ch))
                    this.processNumber();
                break;
        }
    }

    private void processNumber() {
        while (Character.isDigit(this.peek()) || this.peek() == '.')
            this.advance();

        String number = this.expression.substring(this.posStart, this.posCurrent);
        double value = Double.parseDouble(number);
        this.addToken(value, TokenType.NUM);
    }

    private void addToken(double literal, TokenType type) {
        this.tokens.add(new Token(literal, type));
    }

    private char advance() {
        if (this.isAtEnd()) 
            return '\0';
        this.posCurrent++;
        return this.expression.charAt(this.posCurrent - 1);
    }

    private boolean isAtEnd() {
        return this.posCurrent >= this.expression.length();
    }

    private char peek() {
        if (this.isAtEnd()) 
            return '\0';
        return this.expression.charAt(this.posCurrent);
    }
}

enum TokenType {
    NUM,
    PLUS,
    MINUS,
    STAR,
    SLASH,
    LP,
    RP,
    END,
}

class Token {
    public double literal;
    public TokenType type;

    Token(double literal, TokenType type) {
        this.literal = literal;
        this.type = type;
    }
}



