package code.table

import code.CodeGroup
import tools.Constants

object Table {

    var selectedTable = 1

    private val evenSequence = CodeGroup(Constants.EVEN_SEQUENCE)
    val tables = arrayOf(TwoPartTable(Constants.TABLE_NUM_1_PART_1_FILE_NAME,Constants.TABLE_NUM_1_PART_2_FILE_NAME),
        TwoPartTable(Constants.TABLE_NUM_2_PART_1_FILE_NAME,Constants.TABLE_NUM_2_PART_2_FILE_NAME),
        FourPartTable(Constants.TABLE_NUM_3_PART_1_FILE_NAME,Constants.TABLE_NUM_3_PART_2_FILE_NAME,Constants.TABLE_NUM_3_PART_3_FILE_NAME,Constants.TABLE_NUM_3_PART_4_FILE_NAME),
        TwoPartTable(),
        FourPartTable())

    fun superEncode(aCodeGroups: String, aRandomNumber: String): String
    {
        val stringBuilder = StringBuilder()
        val textCodeGroups = aCodeGroups.split(" ")
        val codeGroups = ArrayList<CodeGroup>()

        textCodeGroups.forEach{
            codeGroups.add(CodeGroup(it))
        }

        val randomNumber = CodeGroup(aRandomNumber)

        stringBuilder.append(randomNumber.textValue).append(" ")

        val superCodeGroups = tables[selectedTable-1].superEncode(codeGroups, randomNumber)

        superCodeGroups.forEach {
            stringBuilder.append(it.textValue).append(" ")
        }

        val checkRandomNumber = randomNumber + evenSequence
        stringBuilder.append(checkRandomNumber.textValue)

        return stringBuilder.toString()
    }

    fun superDecode(aSuperCodeGroups: String): Pair<String, String>
    {
        var textSuperCodeGroups = aSuperCodeGroups.split(" ")
        val superCodeGroups = ArrayList<CodeGroup>()

        if(textSuperCodeGroups.size < 2) throw Exception(Constants.FEW_CODE_GROUPS_EXCEPTION)

        val randomNumber = CodeGroup(textSuperCodeGroups.first())
        var checkRandomNumber = CodeGroup(textSuperCodeGroups.last())
        checkRandomNumber -= evenSequence

        if(randomNumber != checkRandomNumber) throw Exception(Constants.CHECK_RANDOM_NUMBER_EXCEPTION)

        textSuperCodeGroups = textSuperCodeGroups.drop(1)
        textSuperCodeGroups = textSuperCodeGroups.dropLast(1)

        textSuperCodeGroups.forEach{
            superCodeGroups.add(CodeGroup(it))
        }

        val codeGroups = tables[selectedTable-1].superDecode(superCodeGroups, randomNumber)

        val stringBuilder = StringBuilder()
        codeGroups.forEach {
            stringBuilder.append(it.textValue).append(" ")
        }

        return Pair(stringBuilder.toString(), randomNumber.textValue)
    }

}