package code.table

import code.CodeGroup
import tools.FileReader
import kotlin.random.Random

abstract class AbstractTable {

    fun initFromFile(partOfTableFileName: String): HashMap<String, ArrayList<CodeGroup>>
    {
        val partOfTable = HashMap<String, ArrayList<CodeGroup>>()

        val bufferedReader = FileReader.readToBufferedRead("zsd_table/$partOfTableFileName.txt")

        for(i in 1..6)
        {
            bufferedReader.readLine()
        }

        for(line in bufferedReader.lines())
        {
            if(line.isNullOrBlank())
                continue

            line.trimEnd()
            val numbersInLine = line.split(" ")
            val lineNumber = numbersInLine[0]

            val numbersList = ArrayList<CodeGroup>()
            for(i in 1 until numbersInLine.size)
            {
                numbersList.add(CodeGroup(numbersInLine[i]))
            }
            partOfTable[lineNumber] = numbersList
        }

        return partOfTable
    }

    fun initRandom(aStartHeight: Int, aEndHeight: Int, aWidth: Int): HashMap<String, ArrayList<CodeGroup>>
    {
        val partOfTable = HashMap<String, ArrayList<CodeGroup>>()

        for(i in aStartHeight..aEndHeight)
        {
            val numbersList = ArrayList<CodeGroup>()
            for(j in 0 until aWidth)
            {
                var randomNumber = generateRandomFiveDigitCodeGroup()
                while(numbersList.contains(randomNumber)) randomNumber = generateRandomFiveDigitCodeGroup()
                numbersList.add(randomNumber)
            }
            partOfTable[String.format("%02d",i)] = numbersList
        }

        return partOfTable
    }

    private fun sum(a: CodeGroup, b: CodeGroup) = a + b
    private fun sub(a: CodeGroup, b: CodeGroup) = a - b

    private fun superCode(codeGroups: ArrayList<CodeGroup>, randomNumber: CodeGroup, operation: (CodeGroup, CodeGroup) -> CodeGroup): ArrayList<CodeGroup>
    {
        val retCodeGroups = ArrayList<CodeGroup>()

        val row = randomNumber.numberValue[0]*10 + randomNumber.numberValue[1]
        val column = randomNumber.numberValue[2]*10 + randomNumber.numberValue[3]
        val direction = (randomNumber.numberValue[4] % 2) == 0

        val codeGroupSequence = getCodeGroupSequence(row, column, direction, codeGroups.size)

        for(i in 0 until codeGroups.size)
        {
            retCodeGroups.add(operation(codeGroups[i], codeGroupSequence[i]))
        }

        return retCodeGroups
    }

    fun superEncode(codeGroups: ArrayList<CodeGroup>, randomNumber: CodeGroup): ArrayList<CodeGroup>
    {
        return superCode(codeGroups, randomNumber, ::sum)
    }

    fun superDecode(codeGroups: ArrayList<CodeGroup>, randomNumber: CodeGroup): ArrayList<CodeGroup>
    {
        return superCode(codeGroups, randomNumber, ::sub)
    }

    abstract fun getCodeGroupSequence(aRow: Int, aColumn: Int, direction: Boolean, size: Int): ArrayList<CodeGroup>

    private fun generateRandomFiveDigitCodeGroup(): CodeGroup
    {
        val stringBuilder = StringBuilder()
        for(i in 1..5)
        {
            stringBuilder.append(Random.nextInt(10))
        }
        return CodeGroup(stringBuilder.toString())
    }

}