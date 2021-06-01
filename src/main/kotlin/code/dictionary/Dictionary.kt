package code.dictionary

object Dictionary {

    val pages: ArrayList<Page> = ArrayList()
    private val punctuation: HashMap<String, String> = HashMap()
    private var userDefinedWords: HashMap<String, ArrayList<String>> = HashMap()

    init {
        for(number in 2..212)
        {
            val page = Page(String.format("%03d",number))
            pages.add(page)
        }

        punctuation["00001"] = "’"
        punctuation["01001"] = ","
        punctuation["02001"] = "."
        punctuation["03001"] = ":"
        punctuation["04001"] = "/"
        punctuation["05001"] = "?"
        punctuation["06001"] = "—"
        punctuation["07001"] = "-"
        punctuation["08001"] = "."
        punctuation["08001"] = ".\n"
        punctuation["10001"] = "„"
        punctuation["11001"] = "“"
        punctuation["13001"] = "!"
        punctuation["14001"] = "("
        punctuation["15001"] = ")"
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
            if(punctuation[codeGroup] == aWord)
            {
                return codeGroup
            }
        }

        for(codeGroup in userDefinedWords.keys)
        {
            userDefinedWords[codeGroup]?.forEach { if(it == aWord) return codeGroup }
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

        foundWord = punctuation[aCode]
        if(foundWord != null) return foundWord

        foundWord = userDefinedWords[aCode]?.get(0)
        if(foundWord != null) return foundWord

        return ""
    }

    fun addUserDefinedWord(aCode: String, aWord: String)
    {
        if(userDefinedWords.containsKey(aCode))
        {
            userDefinedWords[aCode]?.add(aWord)
        }
        else
        {
            val wordList = ArrayList<String>()
            wordList.add(aWord)
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
                stringBuilder.append("$codeGroup - $word").append("\n")
            }
        }

        return stringBuilder.toString().trim()
    }

}