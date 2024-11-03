data class Position(var idx: Int, var ln:Int, var col:Int, val fName: String,val ftxt:String){

    fun advance(curr: Char):Position{
        this.idx +=1
        this.col +=1
        if (curr == '\n'){
            this.ln +=1
            this.col = 0
        }
        return this
    }

    fun copy(): Position {
        return Position(this.idx,this.ln,this.col,this.fName,this.ftxt)
    }

}