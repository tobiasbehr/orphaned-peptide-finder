package dk.aau.bio.opf.neworf

import dk.aau.bio.opf.model.CsvFile
import dk.aau.bio.opf.model.FastAFile
import tornadofx.property
import java.io.File

/**
 * @author Tobias Behr
 */
class NewOrfInput(peptidesFileReference: File? = null, peptidesColumn: CsvFile.HeaderColumn? = null, accessionColumn: CsvFile.HeaderColumn? = null, referenceDbFileReference: File? = null, targetFolder: File? = null, fastAFile: FastAFile? = null) {
    var peptidesFileReference by property(peptidesFileReference)
    var peptidesColumn by property(peptidesColumn)
    var accessionColumn by property(accessionColumn)
    var referenceDbFileReference by property(referenceDbFileReference)
    var targetFolder by property(targetFolder)

    var peptidesFile: CsvFile? = null
    var fastAFile: FastAFile? = null

    override fun toString(): String {
        println("peptidesFile=${peptidesFileReference}, peptidesColumn=${peptidesColumn}, " +
                "accessionColumn=${accessionColumn}, referenceDbFile=${referenceDbFileReference}, " +
                "targetFolder=${targetFolder}")
        return ""
    }
}