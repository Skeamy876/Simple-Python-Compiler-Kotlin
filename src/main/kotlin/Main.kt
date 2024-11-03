 fun main(){
     val lexer = Lexer("<stdin>","3+3")
     lexer.proccessNextChar()
     val (result,error)= lexer.createTokens()

     if (error != null) println(error.toString()) else println(result)
 }


