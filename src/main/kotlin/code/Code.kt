package code

import code.dictionary.Dictionary

object Code {
    private val dictionary = Dictionary

    fun encode(aWords: String): String
    {
        val wordList = ArrayList<WordGroup>()
        for(word in aWords.split("\\s+|(?=\\W\\p{Punct}|\\p{Punct}\\W)|(?<=\\W\\p{Punct}|\\p{Punct}\\W})".toRegex()))
        {
            if(word.isNotBlank()) wordList.add(WordGroup(word.trimEnd()))
        }

        forLoopEncode(wordList, dictionary::encode)
        forLoopEncode(wordList, ::encodeWithTellers)

        val stringBuilder = StringBuilder()
        wordList.forEach {
            val word = it.value
            if(word.isNotBlank()) stringBuilder.append(word).append(" ")
        }

        return setSpacesBetweenWordsToOne(stringBuilder.toString().trimEnd())
    }

    private fun forLoopEncode(wordList: ArrayList<WordGroup>, encodeFun: (aWord: String) -> String)
    {
        for(i in 1..wordList.size)
        {
            second@ for(j in i downTo 1)
            {
                val stringBuilder = StringBuilder()
                for(k in (i-j)..(wordList.size-j))
                {
                    val wordGroup = wordList[k]
                    if(wordGroup.isChangeable) stringBuilder.append(wordGroup.value).append(" ")
                    else continue@second
                }
                val word = stringBuilder.toString().trimEnd()
                val encodedWord = encodeFun(word)
                if(encodedWord.isNotBlank())
                {
                    for(k in (i-j)..(wordList.size-j))
                    {
                        wordList[k] = WordGroup("", false)
                    }
                    wordList[i-j] = WordGroup(encodedWord, false)
                }
            }
        }
    }

    fun decode(aCodeGroups: String): String
    {
        val wordList = ArrayList<String>()
        for(word in aCodeGroups.split(" "))
        {
            if(word.isNotBlank()) wordList.add(word.trimEnd())
        }

        var restCodeGroups = decodeWithTellers(wordList)
        for(codeGroup in restCodeGroups.split(" "))
        {
            val decodedCodeGroup = dictionary.decode(codeGroup)
            if(decodedCodeGroup.isNotBlank()) restCodeGroups = restCodeGroups.replace(codeGroup, " $decodedCodeGroup ")
        }
        return setSpacesBetweenWordsToOne(restCodeGroups)
    }

    private fun setSpacesBetweenWordsToOne(text: String): String
    {
        val stringBuilder = StringBuilder()

        val wordList = text.split(" ")
        wordList.forEach {
            if(it.isNotBlank()) stringBuilder.append(it.trim()).append(" ")
        }
        stringBuilder.trimEnd()

        return stringBuilder.toString()
    }

    private fun encodeWithTellers(word: String): String
    {
        val capitalized = dictionary.encode(word.capitalize())
        if(capitalized.isNotBlank()) return "$capitalized 23001"

        dictionary.pages.forEach { page ->
            page.getAllWords().forEach {
                if(it.startsWith(word))
                {
                    when(word.length)
                    {
                        2 -> return "${page.findWordNumber(it)}${page.number} 24001"
                        3 -> return "${page.findWordNumber(it)}${page.number} 25001"
                        4 -> return "${page.findWordNumber(it)}${page.number} 26001"
                        5 -> return "${page.findWordNumber(it)}${page.number} 27001"
                        6 -> return "${page.findWordNumber(it)}${page.number} 28001"
                        7 -> return "${page.findWordNumber(it)}${page.number} 29001"
                        8 -> return "${page.findWordNumber(it)}${page.number} 30001"
                    }
                }
            }
        }

        dictionary.pages.forEach { page ->
            page.getAllWords().forEach {
                if(it.dropLast(1) == word) return "${page.findWordNumber(it)}${page.number} 31001"
                if(it.dropLast(2) == word) return "${page.findWordNumber(it)}${page.number} 32001"
                if(it.dropLast(3) == word) return "${page.findWordNumber(it)}${page.number} 33001"
            }
        }

        dictionary.pages.forEach { page ->
            page.getAllWords().forEach {
                val splitWord = it.split(" ")
                if(splitWord.size > 1)
                {
                    if(splitWord[0] == word) return "${page.findWordNumber(it)}${page.number} 34001"
                    if(splitWord.size > 2)
                    {
                        val firstAndSecond = "${splitWord[0]}  ${splitWord[1]}"
                        if(firstAndSecond == word) return "${page.findWordNumber(it)}${page.number} 35001"

                        if(splitWord[1] == word) return "${page.findWordNumber(it)}${page.number} 36001"

                        if(splitWord.last() == word) return "${page.findWordNumber(it)}${page.number} 37001"
                    }
                }
            }
        }

        dictionary.pages.forEach { page ->
            page.getAllWords().forEach {
                if(word.startsWith(it)) {
                    val restOfWord = word.replaceFirst(it, "").trim()
                    val restOfWordCodeGroup = dictionary.encode(restOfWord)
                    if(restOfWordCodeGroup.isNotBlank()) return "${page.findWordNumber(it)}${page.number} $restOfWordCodeGroup 19001"
                }
            }
        }

        dictionary.pages.forEach { firstLoopPage ->
            firstLoopPage.getAllWords().forEach { firstLoopWord ->
                if(word.startsWith(firstLoopWord))
                {
                    val firstWordPart = word.replaceFirst(firstLoopWord,"")
                    dictionary.pages.forEach { secondLoopPage ->
                        secondLoopPage.getAllWords().forEach { secondLoopWord ->
                            if(firstWordPart.startsWith(secondLoopWord))
                            {
                                val secondWordPart = firstWordPart.replaceFirst(secondLoopWord,"")
                                val lastWordPart = dictionary.encode(secondWordPart)
                                if(lastWordPart.isNotBlank())
                                {
                                    println("$firstLoopWord $secondLoopWord $secondWordPart")
                                    return "${firstLoopPage.findWordNumber(firstLoopWord)}${firstLoopPage.number} ${secondLoopPage.findWordNumber(secondLoopWord)}${secondLoopPage.number} $lastWordPart 20001"
                                }
                            }
                        }
                    }
                }
            }
        }

        var counter = 0
        val stringBuilder = StringBuilder()
        for(i in word)
        {
            val encodedChar = dictionary.encode(i.toString())
            if(encodedChar.isNotBlank()) {
                stringBuilder.append(encodedChar).append(" ")
                counter++
            }
            else break
        }
        if(word.length == counter) return "${stringBuilder.trimEnd()} 21001"


        return ""
    }

    private fun decodeWithTellers(codeGroups: ArrayList<String>): String
    {
        for(i in 0 until codeGroups.size)
        {
            when(codeGroups[i])
            {
                "23001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.capitalize()
                            codeGroups[i] = ""
                        }
                    }
                }

                "24001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.substring(0,2)
                            codeGroups[i] = ""
                        }
                    }
                }
                "25001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.substring(0,3)
                            codeGroups[i] = ""
                        }
                    }
                }
                "26001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.substring(0,4)
                            codeGroups[i] = ""
                        }
                    }
                }
                "27001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.substring(0,5)
                            codeGroups[i] = ""
                        }
                    }
                }
                "28001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.substring(0,6)
                            codeGroups[i] = ""
                        }
                    }
                }
                "29001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.substring(0,7)
                            codeGroups[i] = ""
                        }
                    }
                }
                "30001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.substring(0,8)
                            codeGroups[i] = ""
                        }
                    }
                }

                "31001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.dropLast(1)
                            codeGroups[i] = ""
                        }
                    }
                }
                "32001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.dropLast(2)
                            codeGroups[i] = ""
                        }
                    }
                }
                "33001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            codeGroups[index] = decodedCodeGroup.dropLast(3)
                            codeGroups[i] = ""
                        }
                    }
                }

                "34001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            val splitDecodedCodeGroup = decodedCodeGroup.split(" ")
                            if (splitDecodedCodeGroup.size > 1) {
                                codeGroups[index] = splitDecodedCodeGroup[0]
                                codeGroups[i] = ""
                            }
                        }
                    }
                }
                "35001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            val splitDecodedCodeGroup = decodedCodeGroup.split(" ")
                            if (splitDecodedCodeGroup.size > 2) {
                                codeGroups[index] = "${splitDecodedCodeGroup[0]} ${splitDecodedCodeGroup[1]}"
                                codeGroups[i] = ""
                            }
                        }
                    }
                }
                "36001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            val splitDecodedCodeGroup = decodedCodeGroup.split(" ")
                            if (splitDecodedCodeGroup.size > 2) {
                                codeGroups[index] = splitDecodedCodeGroup[1]
                                codeGroups[i] = ""
                            }
                        }
                    }
                }
                "37001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                            val splitDecodedCodeGroup = decodedCodeGroup.split(" ")
                            if (splitDecodedCodeGroup.size > 2) {
                                codeGroups[index] = splitDecodedCodeGroup.last()
                                codeGroups[i] = ""
                            }
                        }
                    }
                }

                "19001" -> {
                    val index = i-2
                    if(index >= 0)
                    {
                        val firstCodeGroupToDecode = codeGroups[index]
                        val secondCodeGroupToDecode = codeGroups[index + 1]
                        val decodedFirstCodeGroup = dictionary.decode(firstCodeGroupToDecode)
                        val decodedSecondCodeGroup = dictionary.decode(secondCodeGroupToDecode)

                        if(decodedFirstCodeGroup.isNotBlank() && decodedSecondCodeGroup.isNotBlank()) {
                            codeGroups[index] = "$decodedFirstCodeGroup$decodedSecondCodeGroup"
                            codeGroups[index + 1] = ""
                            codeGroups[index + 2] = ""
                        }
                    }
                }
                "20001" -> {
                    val index = i-3
                    if(index >= 0)
                    {
                        val firstCodeGroupToDecode = codeGroups[index]
                        val secondCodeGroupToDecode = codeGroups[index + 1]
                        val thirdCodeGroupToDecode = codeGroups[index + 2]
                        val decodedFirstCodeGroup = dictionary.decode(firstCodeGroupToDecode)
                        val decodedSecondCodeGroup = dictionary.decode(secondCodeGroupToDecode)
                        val decodedThirdCodeGroup = dictionary.decode(thirdCodeGroupToDecode)

                        if(decodedFirstCodeGroup.isNotBlank() && decodedSecondCodeGroup.isNotBlank() && decodedThirdCodeGroup.isNotBlank()) {
                            codeGroups[index] = "$decodedFirstCodeGroup$decodedSecondCodeGroup$decodedThirdCodeGroup"
                            codeGroups[index + 1] = ""
                            codeGroups[index + 2] = ""
                            codeGroups[index + 3] = ""
                        }
                    }
                }
                "21001" -> {
                    var valid = true
                    val stringBuilder = StringBuilder()
                    for(index in 0 until i)
                    {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) stringBuilder.append(decodedCodeGroup)
                        else {
                            valid = false
                            break
                        }
                    }
                    if(valid) return stringBuilder.toString().trimEnd()
                }

                "12001" -> {
                    val index = i-1
                    if(index >= 0) {
                        val codeGroupToDecode = codeGroups[index]
                        val decodedCodeGroup = dictionary.decode(codeGroupToDecode)
                        if(decodedCodeGroup.isNotBlank()) {
                                codeGroups[index] = "„$decodedCodeGroup“"
                                codeGroups[i] = ""
                        }
                    }
                }

            }


        }

        val stringBuilder = StringBuilder()
        codeGroups.forEach {
            if(it.isNotBlank()) stringBuilder.append(it).append(" ")
        }

        return stringBuilder.trimEnd().toString()
    }
}