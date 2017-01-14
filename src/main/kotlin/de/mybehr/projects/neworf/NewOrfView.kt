package de.mybehr.projects.neworf

import de.mybehr.projects.model.CsvFile
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

    var peptidesColumnCombo: ComboBox<CsvFile.HeaderColumn> by singleAssign()
    var accessionColumnCombo: ComboBox<CsvFile.HeaderColumn> by singleAssign()

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
                        peptidesColumnCombo = combobox<CsvFile.HeaderColumn>(model.peptidesColumn)
                    }
                    hbox {
                        label("Accession")
                        accessionColumnCombo = combobox<CsvFile.HeaderColumn>(model.accessionColumn)
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
                            textfield(model.referenceDbFile, FileToStringConverter())
                            button {
                                text = "..."
                                setOnAction {
                                    model.referenceDbFile.value = chooseFile("test", arrayOf()).singleOrNull()
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
                            textfield(model.targetFolder, FileToStringConverter())
                            button {
                                text = "..."
                                setOnAction {
                                    model.targetFolder.value = chooseDirectory("Target Folder")
                                }
                            }
                        }
                    }

                    hbox {
                        button { text = "Cancel" }
                        button {
                            text = "Start"
                        }
                    }
                    hbox {
                        label("Status:")
                        label("Waiting for input")
                    }
                }
            }
        }
    }

    fun setPeptidesHeader(header: List<CsvFile.HeaderColumn>) {
        peptidesColumnCombo.items.addAll(header)
        accessionColumnCombo.items.addAll(header)
    }
}

