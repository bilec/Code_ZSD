package code.dictionary

import tools.FileReader

data class Page(val pageFileName: String)
{
    private val words: HashMap<String, ArrayList<String>> = HashMap() //<number, list of words> <key, value>
    val number: String
    val allWords: Set<String>
        get() {
            val allWords = HashSet<String>()

            for(wordList in words.values)
            {
                wordList.forEach { allWords.add(it) }
            }

            return allWords
        }

    init {
        val bufferedReader = FileReader.readToBufferedRead("zsd_dictionary/$pageFileName.txt")

        number = bufferedReader.readLine().trimEnd()
        bufferedReader.readLine()

        for(line in bufferedReader.lines())
        {
            if(line.isNullOrBlank())
                continue

            val wordNumber = line.substring(0,2)
            val word = line.substring(3).trimEnd()

            if(word.isBlank())
                continue

            if(!words.containsKey(wordNumber))
            {
                val wordList = ArrayList<String>()
                wordList.add(word)

                words[wordNumber] = wordList
            }
            else
            {
                words[wordNumber]!!.add(word)
            }
        }
    }

    fun findWord(aWordNumber: String): String?
    {
        return words[aWordNumber]?.get(0)
    }

    fun findWordNumber(aWord: String): String?
    {
        for(wordNumber in words.keys)
        {
            words[wordNumber]?.forEach { if(it == aWord) return wordNumber }
        }
        return null
    }

}