package de.mybehr.projects.neworf

import de.mybehr.projects.model.CsvFile
import de.mybehr.projects.util.FileToStringConverter
import javafx.geometry.Pos
import javafx.scene.control.Button
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
    var startAnalysisButton: Button by singleAssign()
    var cancelAnalysisButton: Button by singleAssign()

    init {

        with(root) {
            vbox {
                vbox {
                    spacing = 10.toDouble()
                    paddingTop(10.toDouble())
                    paddingBottom(10.toDouble())
                    style = "-fx-border-color: darkblue; -fx-border-style: solid"
                    hbox {
                        label("New Orf Data") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        hbox {
                            prefWidth = 170.0
                            textfield (model.peptidesFileReference, FileToStringConverter())
                            button {
                                text = "..."
                                setOnAction {
                                    model.peptidesFileReference.value = chooseFile("Input File", arrayOf()).singleOrNull()
                                    controller.loadPeptidesHeader()
                                }
                            }
                        }
                    }
                    hbox {
                        label("Peptides") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        peptidesColumnCombo = combobox<CsvFile.HeaderColumn>(model.peptidesColumn) {
                            prefWidth = 170.0
                        }
                    }
                    hbox {
                        label("Accession") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        accessionColumnCombo = combobox<CsvFile.HeaderColumn>(model.accessionColumn) {
                            prefWidth = 170.0
                        }
                    }
                }

                vbox {
                    spacing = 10.toDouble()
                    paddingTop(10.toDouble())
                    paddingBottom(10.toDouble())
                    style = "-fx-border-color: darkblue; -fx-border-style: solid"
                    hbox {
                        label("Reference DB") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        hbox {
                            prefWidth = 170.0
                            textfield(model.referenceDbFileReference, FileToStringConverter())
                            button {
                                text = "..."
                                setOnAction {
                                    model.referenceDbFileReference.value = chooseFile("Reference DB", arrayOf()).singleOrNull()
                                    controller.loadFastAFile()
                                }
                            }
                        }
                    }
                    hbox {
                        prefWidth = 170.0
                        label("Filetype") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        textfield {
                            alignment = Pos.CENTER_LEFT

                        }
                    }
                }

                vbox {
                    spacing = 10.toDouble()
                    paddingTop(10.toDouble())
                    paddingBottom(10.toDouble())
                    style = "-fx-border-color: darkblue; -fx-border-style: solid"
                    hbox {
                        label ("Target") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        hbox {
                            prefWidth = 170.0
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
                        cancelAnalysisButton = button {
                            isDisable = true
                            text = "Cancel"
                        }
                        startAnalysisButton = button {
                            isDisable = false
                            text = "Start"
                            setOnAction {
                                startAnalysis()
                            }
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

    fun startAnalysis() {
        startAnalysisButton.setDisable(true)
        cancelAnalysisButton.setDisable(false)
        model.commit()
        controller.startAnalysis()
    }

    fun setPeptidesHeader(header: List<CsvFile.HeaderColumn>) {
        peptidesColumnCombo.items.addAll(header)
        accessionColumnCombo.items.addAll(header)
    }
}

