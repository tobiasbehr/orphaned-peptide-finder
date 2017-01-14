package de.mybehr.projects.neworf

import de.mybehr.projects.model.CsvFile
import tornadofx.property
import java.io.File

/**
 * @author Tobias Behr
 */
class NewOrfInput(peptidesFile: File? = null, peptidesColumn: CsvFile.HeaderColumn? = null, accessionColumn: CsvFile.HeaderColumn? = null, referenceDbFile: File? = null, targetFolder: File? = null) {
    var peptidesFile by property(peptidesFile)
    var peptidesColumn by property(peptidesColumn)
    var accessionColumn by property(accessionColumn)
    var referenceDbFile by property(referenceDbFile)
    var targetFolder by property(targetFolder)
}