package code.table

import code.CodeGroup
import com.google.common.collect.BiMap

class FourPartTable: AbstractTable {

    constructor(firstPartTableName: String,
                secondPartTableName: String,
                thirdPartTableName: String,
                fourthPartTableName: String)
    {
        firstPartTable = initFromFile(firstPartTableName)
        secondPartTable = initFromFile(secondPartTableName)
        thirdPartTable = initFromFile(thirdPartTableName)
        fourthPartTable = initFromFile(fourthPartTableName)
    }

    constructor()
    {
        firstPartTable = initRandom(0,49,9)
        secondPartTable = initRandom(0,49,8)
        thirdPartTable = initRandom(50,99,9)
        fourthPartTable = initRandom(50,99,8)
    }

    private val firstPartTable: BiMap<String, ArrayList<CodeGroup>>
    private val secondPartTable: BiMap<String, ArrayList<CodeGroup>>
    private val thirdPartTable: BiMap<String, ArrayList<CodeGroup>>
    private val fourthPartTable: BiMap<String, ArrayList<CodeGroup>>

    //codeGroup 58-40-2
    // riadok-stlpec- parna - po riadkoch lavo->pravo
    //              neparna - po stlpcoch hore->dole

    override fun getCodeGroupSequence(aRow: Int, aColumn: Int, direction: Boolean, size: Int): ArrayList<CodeGroup>
    {
        val codeGroupSequence = ArrayList<CodeGroup>()
        var column = aColumn%17
        var row = aRow

        if(row < 50)
        {
            // lava
            if((column) < 9)
            {
                //prva a
                println("${row%50} ${column%9} - $aRow $aColumn")
                if(direction)
                {
                    for(i in 1..size)
                    {
                        firstPartTable[String.format("%02d",row%50)]?.get(column%9)?.let { codeGroupSequence.add(it) }

                        column++
                        if(column%9 == 0) row++
                    }
                }
                else
                {
                    for(i in 1..size)
                    {
                        firstPartTable[String.format("%02d",row%50)]?.get(column%9)?.let { codeGroupSequence.add(it) }

                        row++
                        if(row%50 == 0) column++
                    }
                }
            }
            else
            {
                //druha b
                if(direction)
                {
                    for(i in 1..size)
                    {
                        secondPartTable[String.format("%02",row%50)]?.get(column%8)?.let { codeGroupSequence.add(it) }

                        column++
                        if(column%8 == 0) row++
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
                        secondPartTable[String.format("%02d", row%50)]?.get(column%8)?.let { codeGroupSequence.add(it) }

                        row++
                        if (row % 50 == 0) column++
                        if(column > 99) column = 0
                    }
                }
            }
        }
        else
        {
            //prava
            if(column < 9)
            {
                //prva c
                if(direction)
                {
                    for(i in 1..size)
                    {
                        thirdPartTable[String.format("%02d",row%50+50)]?.get(column%9)?.let { codeGroupSequence.add(it) }

                        column++
                        if(column%9 == 0) row++
                    }
                }
                else
                {
                    for(i in 1..size)
                    {
                        thirdPartTable[String.format("%02d", row % 50 + 50)]?.get(column % 9)?.let { codeGroupSequence.add(it) }

                        row++
                        if(row%50 == 0) column++
                    }
                }
            }
            else
            {
                //druha d
                if(direction)
                {
                    for(i in 1..size)
                    {
                        fourthPartTable[String.format("%02d", row % 50 + 50)]?.get(column % 8)?.let { codeGroupSequence.add(it) }

                        column++
                        if(column%8 == 0) row++
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
                        fourthPartTable[String.format("%02d", row % 50 + 50)]?.get(column % 8)?.let { codeGroupSequence.add(it) }

                        row++
                        if(row%50 == 0) column++
                        if(column > 99) column = 0
                    }
                }
            }
        }

        return codeGroupSequence
    }

    private fun partTableToString(partTable: BiMap<String, ArrayList<CodeGroup>>, aStartHeight: Int, aEndHeight: Int, aStartWidth: Int, aEndWidth: Int): String
    {
        val stringBuilder = StringBuilder()

        for(i in 0..99)
        {
            val columnNumber = i%17
            if(columnNumber == 0) stringBuilder.append("\n").append("      ")
            if(columnNumber in aStartWidth until aEndWidth) stringBuilder.append(String.format("%02d",i)).append("        ")
        }

        stringBuilder.append("\n")

        for(i in aStartHeight..aEndHeight)
        {
            val lineNumber = String.format("%02d",i)
            stringBuilder.append(lineNumber).append(" ")
            for(j in 0 until (aEndWidth-aStartWidth))
            {
                partTable[lineNumber]?.get(j)?.let { stringBuilder.append(it.textValue).append(" ") }
            }
            stringBuilder.append("\n")
        }

        return stringBuilder.toString()
    }

    fun firstPartTableToString(): String
    {
        return partTableToString(firstPartTable, 0, 49, 0, 9)
    }

    fun secondPartTableToString(): String
    {
        return partTableToString(secondPartTable, 0, 49, 9, 17)
    }

    fun thirdPartTableToString(): String
    {
        return partTableToString(thirdPartTable, 50, 99, 0, 9)
    }

    fun fourthPartTableToString(): String
    {
        return partTableToString(fourthPartTable, 50, 99, 9, 17)
    }

}