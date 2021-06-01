package code

import tools.Constants

data class CodeGroup(val textValue: String) {

    val numberValue: ArrayList<Int> = ArrayList()

    init {
        if(textValue.length != Constants.CODE_GROUP_LENGTH) throw Exception(Constants.WRONG_LENGTH_EXCEPTION)

        textValue.forEach {
            if(!Character.isDigit(it)) throw Exception(Constants.IS_NOT_NUMBER_EXCEPTION)

            numberValue.add(Character.getNumericValue(it))
        }
    }

    operator fun plus(codeGroup: CodeGroup): CodeGroup
    {
        val sumList = ArrayList<Int>()

        for(i in 0 until 5)
        {
            var sum = numberValue[i] + codeGroup.numberValue[i]
            if(sum >= 10) sum -= 10
            sumList.add(sum)
        }

        return CodeGroup(numberValueToTextValue(sumList))
    }

    operator fun minus(codeGroup: CodeGroup): CodeGroup
    {
        val subList = ArrayList<Int>()

        for(i in 0 until 5)
        {
            var sub = numberValue[i] - codeGroup.numberValue[i]
            if(sub < 0) sub += 10
            subList.add(sub)
        }

        return CodeGroup(numberValueToTextValue(subList))
    }

    private fun numberValueToTextValue(aNumberValue: ArrayList<Int>): String
    {
        val stringBuilder = StringBuilder()

        aNumberValue.forEach { stringBuilder.append(it) }

        return stringBuilder.toString()
    }

}