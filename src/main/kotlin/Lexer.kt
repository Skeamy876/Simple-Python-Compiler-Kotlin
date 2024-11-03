
class Lexer(val fName: String, val text: String) {
    private var pos: Position = Position(-1,0,-1,fName,this.text)
    private var currChar: Char? = null


    fun proccessNextChar() {
        this.pos.advance(this.currChar.toString()[0])
        if (this.pos.idx < text.length) {
            this.currChar = this.text[pos.idx]
        } else {
            this.currChar = null
        }
    }

    fun createTokens(): Pair<MutableList<Token>,Error?> {
        val tokens = mutableListOf<Token>()
        while (this.currChar != null) {
            when (this.currChar) {
                '\t' ->{
                    proccessNextChar()
                }
                '+' -> {
                    tokens.add(Token(TYPE.TT_PLUS, this.currChar))
                    proccessNextChar()
                }
                '-' -> {
                    tokens.add(Token(TYPE.TT_MINUS, this.currChar))
                    proccessNextChar()
                }
                '*' -> {
                    tokens.add(Token(TYPE.TT_MUL, this.currChar))
                    proccessNextChar()
                }
                '/' -> {
                    tokens.add(Token(TYPE.TT_DIV, this.currChar))
                    proccessNextChar()
                }
                '(' -> {
                    tokens.add(Token(TYPE.TT_LPAREN, this.currChar))
                    proccessNextChar()
                }
                ')' -> {
                    tokens.add(Token(TYPE.TT_RPAREN, this.currChar))
                    proccessNextChar()
                }
                else -> {
                    if (Token.Companion.DIGITS.contains(this.currChar.toString()[0])){
                        tokens.add(evaluateDigits())
                    } else if (this.currChar.toString()[0].isWhitespace()){
                        proccessNextChar()
                    } else{
                        val postStart = this.pos.copy()
                        val char = this.currChar
                        proccessNextChar()
                        return Pair(mutableListOf<Token>(),IllegalCharError(posStart=postStart,
                            posEnd=this.pos, dets = "'"+char+"'"))
                    }

                }
            }
        }
        return Pair(tokens,null)
    }

    fun evaluateDigits() : Token {
        var numStr = ""
        var dotCount: Int = 0
        var curr : Char = this.currChar.toString()[0]
        while (curr!= null && Token.Companion.DIGITS.contains(curr,ignoreCase = true)){
            if (curr == '.'){
                if (dotCount == 1) break
                dotCount +=1
                numStr +='.'
            }else{
                numStr += curr
            }
            proccessNextChar()
            curr = this.currChar.toString()[0]
        }
        return if (dotCount == 0) Token(TYPE.TT_INT, numStr.toString().toInt()) else Token(TYPE.TT_FLOAT,numStr.toString().toFloat())
    }

    data class Result<T,E>(var tokens:T,var errors: E )
}
