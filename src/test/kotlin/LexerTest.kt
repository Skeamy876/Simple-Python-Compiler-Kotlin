import kotlin.test.*

class LexerTests {

    @Test
    fun testSingleOperators() {
        val lexer = Lexer("test", "+-*/()")
        lexer.proccessNextChar()

        val (tokens, error) = lexer.createTokens()

        assertNull(error, "Unexpected error during lexing.")
        assertEquals(6, tokens.size, "Expected 6 tokens.")
        assertEquals(TYPE.TT_PLUS, tokens[0].type)
        assertEquals(TYPE.TT_MINUS, tokens[1].type)
        assertEquals(TYPE.TT_MUL, tokens[2].type)
        assertEquals(TYPE.TT_DIV, tokens[3].type)
        assertEquals(TYPE.TT_LPAREN, tokens[4].type)
        assertEquals(TYPE.TT_RPAREN, tokens[5].type)
    }

    @Test
    fun testWhitespaceHandling() {
        val lexer = Lexer("test", "  \t  + - \n  * /")
        lexer.proccessNextChar()

        val (tokens, error) = lexer.createTokens()

        assertNull(error, "Unexpected error during lexing.")
        assertEquals(4, tokens.size, "Expected 4 tokens ignoring whitespace.")
        assertEquals(TYPE.TT_PLUS, tokens[0].type)
        assertEquals(TYPE.TT_MINUS, tokens[1].type)
        assertEquals(TYPE.TT_MUL, tokens[2].type)
        assertEquals(TYPE.TT_DIV, tokens[3].type)
    }

    @Test
    fun testIntegerTokenization() {
        val lexer = Lexer("test", "123")
        lexer.proccessNextChar()

        val (tokens, error) = lexer.createTokens()

        assertNull(error, "Unexpected error during lexing.")
        assertEquals(1, tokens.size, "Expected 1 token.")
        assertEquals(TYPE.TT_INT, tokens[0].type)
        assertEquals(123, tokens[0].value)
    }

    @Test
    fun testFloatTokenization() {
        val lexer = Lexer("test", "123.456")
        lexer.proccessNextChar()

        val (tokens, error) = lexer.createTokens()

        assertNull(error, "Unexpected error during lexing.")
        assertEquals(1, tokens.size, "Expected 1 token.")
        assertEquals(TYPE.TT_FLOAT, tokens[0].type)
        assertEquals(123.456f, tokens[0].value)
    }

    @Test
    fun testInvalidCharacterError() {
        val lexer = Lexer("test", "7*$$")
        lexer.proccessNextChar()
        val (tokens, error) = lexer.createTokens()

        assertNotNull(error, "Expected an error for invalid character.")
        assertTrue(error is IllegalCharError, "Expected an IllegalCharError.")
        assertTrue(tokens.isEmpty(), "Expected no tokens due to error.")
    }

    @Test
    fun testMixedOperatorsAndNumbers() {
        val lexer = Lexer("test", "3 + 5")
        lexer.proccessNextChar()

        val (tokens, error) = lexer.createTokens()

        assertNull(error, "Unexpected error during lexing.")
        assertEquals(3, tokens.size, "Expected 9 tokens for the input.")

        assertEquals(TYPE.TT_INT, tokens[0].type)
        assertEquals(3, tokens[0].value)

        assertEquals(TYPE.TT_PLUS, tokens[1].type)

        assertEquals(TYPE.TT_INT, tokens[2].type)
        assertEquals(5, tokens[2].value)

    }
}
