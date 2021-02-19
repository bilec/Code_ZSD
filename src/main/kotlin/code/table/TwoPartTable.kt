package code.table

import code.CodeGroup
import com.google.common.collect.BiMap
import java.lang.StringBuilder

class TwoPartTable: AbstractTable {

    constructor(firstPartTableName: String,
                secondPartTableName: String)
    {
        firstPartTable = initFromFile(firstPartTableName)
        secondPartTable = initFromFile(secondPartTableName)
    }

    constructor()
    {
        firstPartTable = initRandom(0, 49,17)
        secondPartTable = initRandom(50, 99,17)
    }

    private val firstPartTable: BiMap<String, ArrayList<CodeGroup>>
    private val secondPartTable: BiMap<String, ArrayList<CodeGroup>>

    //codeGroup 58-40-2
    // riadok-stlpec- parna - po riadkoch lavo->pravo
    //              neparna - po stlpcoch hore->dole

    override fun getCodeGroupSequence(aRow: Int, aColumn: Int, direction: Boolean, size: Int): ArrayList<CodeGroup>
    {
        val codeGroupSequence = ArrayList<CodeGroup>()
        var column = aColumn
        var row = aRow

        val tableToUse: BiMap<String, ArrayList<CodeGroup>>
        val rowAdd: Int
        if(aRow < 50)
        {
            tableToUse = firstPartTable
            rowAdd = 0
        }
        else
        {
            tableToUse = secondPartTable
            rowAdd = 50
        }

        if(direction)
        {
            //po riadkoch
            for(i in 1..size)
            {
                tableToUse[String.format("%02d", (row%50)+rowAdd)]?.get(column % 17)?.let { codeGroupSequence.add(it) }

                column++
                if(column%17 == 0) row++
                if(column > 99)
                {
                    column = 0
                    row++
                }
            }
        }
        else
        {
            for(i in 1..size)
            {
                tableToUse[String.format("%02d", (row%50)+rowAdd)]?.get(column % 17)?.let { codeGroupSequence.add(it) }

                row++
                if(row%50 == 0) column++
                if(column > 99) column = 0

            }
        }


        return codeGroupSequence
    }

    private fun partTableToString(partTable: BiMap<String, ArrayList<CodeGroup>>, startHeight: Int, endHeight: Int): String
    {
        val stringBuilder = StringBuilder()

        for(i in 0..99)
        {
            if(i%17 == 0) stringBuilder.append("\n").append("      ")
            stringBuilder.append(String.format("%02d",i)).append("        ")

        }

        stringBuilder.append("\n")

        for(i in startHeight..endHeight)
        {
            val lineNumber = String.format("%02d",i)
            stringBuilder.append(lineNumber).append(" ")
            for(j in 0..16)
            {
                partTable[lineNumber]?.get(j)?.let { stringBuilder.append(it.textValue).append(" ") }
            }
            stringBuilder.append("\n")
        }

        return stringBuilder.toString()
    }

    fun firstPartTableToString(): String
    {
        return partTableToString(firstPartTable, 0, 49)
    }

    fun secondPartTableToString(): String
    {
        return partTableToString(secondPartTable, 50, 99)
    }

}