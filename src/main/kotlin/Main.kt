 fun main(){
     val lexer = Lexer("<stdin>","3+3.5")
     lexer.proccessNextChar()
     val (result,error)= lexer.createTokens()

     if (error != null) println(error.toString()) else println(result)
 }


