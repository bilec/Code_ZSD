package code.dictionary

object Dictionary {

    val pages: ArrayList<Page> = ArrayList()
    private val punctuation: HashMap<String, Word> = HashMap()
    private var userDefinedWords: HashMap<String, ArrayList<Word>> = HashMap()

    init {
        for(number in 2..212)
        {
            val page = Page(String.format("%03d",number))
            pages.add(page)
        }

        punctuation["00001"] = Word("’")
        punctuation["01001"] = Word(",")
        punctuation["02001"] = Word(".")
        punctuation["03001"] = Word(":")
        punctuation["04001"] = Word("/")
        punctuation["05001"] = Word("?")
        punctuation["06001"] = Word("—")
        punctuation["07001"] = Word("-")
        punctuation["08001"] = Word(".")
        punctuation["08001"] = Word(".\n")
        punctuation["10001"] = Word("„")
        punctuation["11001"] = Word("“")
        punctuation["13001"] = Word("!")
        punctuation["14001"] = Word("(")
        punctuation["15001"] = Word(")")
    }

    fun encode(aWord: String): String
    {
        pages.forEach {
            val wordNumber = it.findWordNumber(aWord)
            if(wordNumber != null)
            {
                return wordNumber + it.number
            }
        }

        for(codeGroup in punctuation.keys)
        {
            if(punctuation[codeGroup]?.value == aWord)
            {
                return codeGroup
            }
        }

        for(codeGroup in userDefinedWords.keys)
        {
            userDefinedWords[codeGroup]?.forEach { if(it.value == aWord) return codeGroup }
        }

        return ""
    }

    fun decode(aCode: String): String
    {
        val wordNumber = aCode.substring(0,2)
        val pageNumber = aCode.substring(2)
        var foundWord: String?

        val foundPage = pages.find { it.number == pageNumber }
        if(foundPage != null)
        {
            foundWord = foundPage.findWord(wordNumber)
            if(foundWord != null) return foundWord
        }

        foundWord = punctuation[aCode]?.value
        if(foundWord != null) return foundWord

        foundWord = userDefinedWords[aCode]?.get(0)?.value
        if(foundWord != null) return foundWord

        return ""
    }

    fun addUserDefinedWord(aCode: String, aWord: String)
    {
        if(userDefinedWords.containsKey(aCode))
        {
            userDefinedWords[aCode]?.add(Word(aWord))
        }
        else
        {
            val wordList = ArrayList<Word>()
            wordList.add(Word(aWord))
            userDefinedWords[aCode] = wordList
        }
    }

    fun userDefinedWordsToString(): String
    {
        val stringBuilder = StringBuilder()

        for(codeGroup in userDefinedWords.keys)
        {
            for(word in userDefinedWords[codeGroup]!!)
            {
                stringBuilder.append("$codeGroup - ${word.value}").append("\n")
            }
        }

        return stringBuilder.toString().trim()
    }

}