
open class Error{
    open val positionStart: Position? = null
    open val postionEnd: Position? =  null
    open val errorName: String = ""
    open val details: String = ""


    override fun toString(): String {
        return """$errorName : $details
            |File ${this.positionStart?.fName}, line ${this.positionStart?.ln}
        """.trimMargin()
    }

}

 class IllegalCharError(val posStart:Position,val posEnd: Position, val dets:String): Error() {
     override val errorName: String
         get() = "Illegal Character"

     override val positionStart: Position
         get() = posStart
     override val postionEnd: Position
         get() = posEnd
     override val details: String
         get() = dets
 }

