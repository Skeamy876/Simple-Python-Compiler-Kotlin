class Token(var type: TYPE, val value: Any?) {

    //TODO: implement regex matching for call token matching
    companion object{
        val DIGITS = "0123456789."

    }
    override fun toString(): String {
        if (this.value != null) return "${this.type.toString()} : ${this.value}"
        return "${this.type}"
    }
}
enum class TYPE(repr: String) {
    TT_INT(repr = "INT"),
    TT_FLOAT(repr = "FLOAT"),
    TT_PLUS(repr = "PLUS"),
    TT_MINUS(repr = "MINUS"),
    TT_MUL(repr = "MUL"),
    TT_DIV(repr = "DIV"),
    TT_LPAREN(repr = "LPAREN"),
    TT_RPAREN(repr = "RPAREN")
}




