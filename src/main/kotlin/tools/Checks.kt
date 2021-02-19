package tools

import code.CodeGroup

class Checks {

    companion object {
        fun isCodeGroupCorrect(codeGroup: String?): Boolean {
            if (codeGroup.isNullOrBlank()) return false
            try {
                CodeGroup(codeGroup)
            } catch (e: Exception) {
                return false
            }
            return true
        }

        fun areCodeGroupsCorrect(codeGroups: List<String>): Boolean {
            for (textCodeGroup in codeGroups) {
                if (!isCodeGroupCorrect(textCodeGroup)) return false
            }
            return true
        }
    }
}