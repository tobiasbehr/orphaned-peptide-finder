package de.mybehr.projects.neworf

import de.mybehr.projects.util.FileToStringConverter
import javafx.scene.control.ComboBox
import javafx.scene.layout.AnchorPane
import tornadofx.*

/**
 * @author Tobias Behr
 */
class NewOrfView : View("--- TITLE MISSING ---") {
    override val root = AnchorPane()

    val model: NewOrfModel by inject()

    val controller: NewOrfController by inject()

    var peptidesColumnCombo: ComboBox<String> by singleAssign()
    var accessionColumnCombo: ComboBox<String> by singleAssign()

    init {

        with(root) {
            vbox {
                vbox {
                    spacing = 10.toDouble()
                    paddingTop(10.toDouble())
                    paddingBottom(10.toDouble())
                    style = "-fx-border-color: darkblue; -fx-border-style: solid"
                    hbox {
                        label("New Orf Data")
                        hbox {
                            textfield (model.peptidesFile, FileToStringConverter())
                            button {
                                text = "..."
                                setOnAction {
                                    model.peptidesFile.value = chooseFile("test", arrayOf()).singleOrNull()
                                    controller.loadPeptidesHeader()
                                }
                            }
                        }
                    }
                    hbox {
                        label("Peptides")
                        peptidesColumnCombo = combobox<String> {
                        }
                    }
                    hbox {
                        label("Accession")
                        accessionColumnCombo = combobox<String> { }
                    }
                }

                vbox {
                    spacing = 10.toDouble()
                    paddingTop(10.toDouble())
                    paddingBottom(10.toDouble())
                    style = "-fx-border-color: darkblue; -fx-border-style: solid"
                    hbox {
                        label("Reference DB")
                        hbox {
                            textfield { }
                            button {
                                text = "..."
                                setOnAction {
                                    println("refernceDb button clicked")
                                }
                            }
                        }
                    }
                    hbox {
                        label("Filetype")
                        textfield { }
                    }
                }

                vbox {
                    spacing = 10.toDouble()
                    paddingTop(10.toDouble())
                    paddingBottom(10.toDouble())
                    style = "-fx-border-color: darkblue; -fx-border-style: solid"
                    hbox {
                        label ("Target")
                        hbox {
                            textfield { }
                            button {
                                text = "..."
                                setOnAction { println("Button target clicked") }
                            }
                        }
                    }

                    hbox {
                        button { text = "Cancel" }
                        button { text = "Start" }
                    }
                    hbox {
                        label("Status:")
                        label("Waiting for input")
                    }
                }
            }
        }
    }

    fun setPeptidesHeader(header: List<String>) {
        peptidesColumnCombo.items.addAll(header)
        accessionColumnCombo.items.addAll(header)
    }
}

