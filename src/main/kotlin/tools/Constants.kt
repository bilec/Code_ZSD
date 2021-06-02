package tools

import java.awt.Dimension
import java.util.*

class Constants {

    companion object{
        const val APP_NAME = "Šifrovací kód ZSD"

        const val CODE_GROUP_LENGTH = 5

        const val EVEN_SEQUENCE = "24680"

        const val ENCODE_TAB = "Šifrovanie"
        const val DECODE_TAB = "Dešifrovanie"
        const val SUPER_ENCODE_TAB = "Prešifrovanie"
        const val SUPER_DECODE_TAB = "Dešifrovanie prešifrovania"
        const val NEW_WORDS_TAB = "Pridanie nových slov"
        const val SHOW_DICTIONARY_TAB = "Zobrazenie slovníka a tabuliek"

        const val ENCODE_BUTTON = "Šifruj"
        const val DECODE_BUTTON = "Dešifruj"
        const val SUPER_ENCODE_BUTTON = "Prešifruj"
        const val SUPER_DECODE_BUTTON = "Dešifruj"
        const val CLEAN_BUTTON = "Vymaž všetko"
        const val COPY_BUTTON = "Kopíruj výstup"
        const val PASTE_BUTTON = "Vlož vstup"
        const val SAVE_BUTTON = "Ulož"
        const val RANDOM_NUMBER_BUTTON = "Vygeneruj náhodné číslo"

        const val INPUT_LABEL = "Vstup: "
        const val OUTPUT_LABEL = "Výstup: "
        const val WORD_INPUT_LABEL = "Vstup slova: "
        const val CODE_INPUT_LABEL = "Vstup kódovej skupiny: "

        private const val TABLE_NUM_1 = "Tabuľka č. 1"
        private const val TABLE_NUM_2 = "Tabuľka č. 2"
        private const val TABLE_NUM_3 = "Tabuľka č. 3"
        private const val TABLE_NUM_4 = "Náhodná tabuľka č. 4"
        private const val TABLE_NUM_5 = "Náhodná tabulka c. 5"
        val TABLES = arrayOf(TABLE_NUM_1, TABLE_NUM_2, TABLE_NUM_3, TABLE_NUM_4, TABLE_NUM_5)

        const val TABLE_NUM_1_PART_1_FILE_NAME = "Tabulka_1_lava"
        const val TABLE_NUM_1_PART_2_FILE_NAME = "Tabulka_1_prava"

        const val TABLE_NUM_2_PART_1_FILE_NAME = "Tabulka_2_lava"
        const val TABLE_NUM_2_PART_2_FILE_NAME = "Tabulka_2_prava"

        const val TABLE_NUM_3_PART_1_FILE_NAME = "Tabulka_3_lava_prva_cast"
        const val TABLE_NUM_3_PART_2_FILE_NAME = "Tabulka_3_lava_druha_cast"
        const val TABLE_NUM_3_PART_3_FILE_NAME = "Tabulka_3_prava_prva_cast"
        const val TABLE_NUM_3_PART_4_FILE_NAME = "Tabulka_3_prava_druha_cast"

        const val SHOW_ENTRY_USER_DEFINED_WORDS = "Používateľom zadané slová"

        const val SHOW_ENTRY_DICTIONARY_PREFIX = "Slovník strana č."
        const val SHOW_ENTRY_TABLE_PREFIX = "Tabuľka č."
        const val SHOW_ENTRY_RANDOM_TABLE_PREFIX = "Náhodná tabuľka č."

        const val SHOW_ENTRY_FIRST_PAGE_POSTFIX = "prvá strana"
        const val SHOW_ENTRY_SECOND_PAGE_POSTFIX = "druhá strana"
        const val SHOW_ENTRY_THIRD_PAGE_POSTFIX = "tretia strana"
        const val SHOW_ENTRY_FOURTH_PAGE_POSTFIX = "štvrtá strana"


        private const val TABLE_NUM_1_PART_1 = "$SHOW_ENTRY_TABLE_PREFIX 1 - $SHOW_ENTRY_FIRST_PAGE_POSTFIX"
        private const val TABLE_NUM_1_PART_2 = "$SHOW_ENTRY_TABLE_PREFIX 1 - $SHOW_ENTRY_SECOND_PAGE_POSTFIX"

        private const val TABLE_NUM_2_PART_1 = "$SHOW_ENTRY_TABLE_PREFIX 2 - $SHOW_ENTRY_FIRST_PAGE_POSTFIX"
        private const val TABLE_NUM_2_PART_2 = "$SHOW_ENTRY_TABLE_PREFIX 2 - $SHOW_ENTRY_SECOND_PAGE_POSTFIX"

        private const val TABLE_NUM_3_PART_1 = "$SHOW_ENTRY_TABLE_PREFIX 3 - $SHOW_ENTRY_FIRST_PAGE_POSTFIX"
        private const val TABLE_NUM_3_PART_2 = "$SHOW_ENTRY_TABLE_PREFIX 3 - $SHOW_ENTRY_SECOND_PAGE_POSTFIX"
        private const val TABLE_NUM_3_PART_3 = "$SHOW_ENTRY_TABLE_PREFIX 3 - $SHOW_ENTRY_THIRD_PAGE_POSTFIX"
        private const val TABLE_NUM_3_PART_4 = "$SHOW_ENTRY_TABLE_PREFIX 3 - $SHOW_ENTRY_FOURTH_PAGE_POSTFIX"

        private const val TABLE_NUM_4_PART_1 = "$SHOW_ENTRY_RANDOM_TABLE_PREFIX 4 - $SHOW_ENTRY_FIRST_PAGE_POSTFIX"
        private const val TABLE_NUM_4_PART_2 = "$SHOW_ENTRY_RANDOM_TABLE_PREFIX 4 - $SHOW_ENTRY_SECOND_PAGE_POSTFIX"

        private const val TABLE_NUM_5_PART_1 = "$SHOW_ENTRY_RANDOM_TABLE_PREFIX 5 - $SHOW_ENTRY_FIRST_PAGE_POSTFIX"
        private const val TABLE_NUM_5_PART_2 = "$SHOW_ENTRY_RANDOM_TABLE_PREFIX 5 - $SHOW_ENTRY_SECOND_PAGE_POSTFIX"
        private const val TABLE_NUM_5_PART_3 = "$SHOW_ENTRY_RANDOM_TABLE_PREFIX 5 - $SHOW_ENTRY_THIRD_PAGE_POSTFIX"
        private const val TABLE_NUM_5_PART_4 = "$SHOW_ENTRY_RANDOM_TABLE_PREFIX 5 - $SHOW_ENTRY_FOURTH_PAGE_POSTFIX"

        private val TABLE_PARTS = arrayOf(TABLE_NUM_1_PART_1, TABLE_NUM_1_PART_2, TABLE_NUM_2_PART_1, TABLE_NUM_2_PART_2,
            TABLE_NUM_3_PART_1, TABLE_NUM_3_PART_2, TABLE_NUM_3_PART_3, TABLE_NUM_3_PART_4, TABLE_NUM_4_PART_1, TABLE_NUM_4_PART_2,
            TABLE_NUM_5_PART_1, TABLE_NUM_5_PART_2, TABLE_NUM_5_PART_3, TABLE_NUM_5_PART_4)

        private fun getShowEntries(): Vector<String>
        {
            val retList = Vector<String>()
            for(i in 1..212)
            {
                retList.add("$SHOW_ENTRY_DICTIONARY_PREFIX ${String.format("%03d",i)}")
            }
            retList.add(SHOW_ENTRY_USER_DEFINED_WORDS)
            retList.addAll(TABLE_PARTS)
            return retList
        }
        val SHOW_ENTRIES = getShowEntries()

        val MAXIMUM_PANEL_SIZE = Dimension(900,50)

        const val WARNING = "Upozornenie"
        const val WORD_CANT_BE_BLANK = "Vstup pre slovo nemôže byť prázdny"
        const val CODE_CANT_BE_BLANK = "Vstup pre kódovú skupinu nemôže byť prázdny"
        const val CODE_GROUP_ERROR = "Chyba v kódovej skupine"
        const val WRONG_INPUT = "Nesprávny vstup"
        const val WRONG_RANDOM_NUMBER = "Nesprávne náhodné číslo"

        const val IS_NOT_NUMBER_EXCEPTION = "CODE_GROUP MUST BE NUMBER"
        const val WRONG_LENGTH_EXCEPTION = "CODE_GROUP MUST HAVE LENGTH $CODE_GROUP_LENGTH"
        const val FEW_CODE_GROUPS_EXCEPTION = "THERE MUST BE AT LEAST TWO CODEGROUPS"
        const val CHECK_RANDOM_NUMBER_EXCEPTION = "CHECKSUM FOR RANDOM NUMBER ERROR"
    }

}