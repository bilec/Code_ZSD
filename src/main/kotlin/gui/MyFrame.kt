package gui

import gui.jpanels.*
import tools.Constants
import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JTabbedPane

object MyFrame: JFrame()
{
    init {
        title = Constants.APP_NAME

        val jTabbedPane = JTabbedPane()

        jTabbedPane.addTab(Constants.ENCODE_TAB, EncodePanel())
        jTabbedPane.addTab(Constants.DECODE_TAB, DecodePanel())
        jTabbedPane.addTab(Constants.SUPER_ENCODE_TAB, SuperEncodePanel())
        jTabbedPane.addTab(Constants.SUPER_DECODE_TAB, SuperDecodePanel())
        jTabbedPane.addTab(Constants.NEW_WORDS_TAB, NewWordsPanel())
        jTabbedPane.addTab(Constants.SHOW_DICTIONARY_TAB, ShowDictionaryPanel())

        add(jTabbedPane)

        defaultCloseOperation = EXIT_ON_CLOSE
        iconImage = ImageIcon(javaClass.classLoader.getResource("icon/icon.png")).image

        size = Dimension(900, 600)
        minimumSize = Dimension(600, 500)

        isVisible = true
    }
}