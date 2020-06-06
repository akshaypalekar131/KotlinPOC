package akshay.com.kotlinbeginnerapp.utils

import android.os.Environment
import android.os.StatFs
import java.util.*

class StorageUtils {

    companion object {
        private const val IS_TOTAL_STORAGE = 0
        private const val IS_TOTAL_AVAILABLE_STORAGE = 1
        private const val IS_TOTAL_USED_STORAGE = 2

        private fun getStatFs() : StatFs?{
            val file = Environment.getDataDirectory()
            return StatFs(file.absolutePath)
        }

        private fun getTotalAvailableStorageSize(): Long{
            val statFs = getStatFs()
            if(statFs!=null){
                return statFs.blockSizeLong * statFs.blockCountLong
            }

            return 0
        }

        private fun getTotalFreeStorageSize():Long{
            val statFs = getStatFs()
            if(statFs!=null){
                return statFs.blockSizeLong * statFs.availableBlocksLong
            }

            return 0
        }

        private fun getTotalUsedStorageSize() = getTotalAvailableStorageSize() - getTotalFreeStorageSize()


        fun convertLongDataToString(storageAvailableType:Int) : String{
            var bytes : Long = 0

            when(storageAvailableType){
                IS_TOTAL_STORAGE ->{
                    bytes = getTotalAvailableStorageSize()
                }

                IS_TOTAL_AVAILABLE_STORAGE ->{
                    bytes = getTotalFreeStorageSize()
                }

                IS_TOTAL_USED_STORAGE ->{
                    bytes = getTotalUsedStorageSize()
                }

            }

            val unit = 1000
            if (bytes < unit) return bytes.toString() + " B"
            val exp = (Math.log(bytes.toDouble()) / Math.log(unit.toDouble())).toInt()
            val pre = "kMGTPE"[exp - 1] + ""
            return String.format(Locale.ENGLISH, "%.1f %sB", bytes / Math.pow(unit.toDouble(), exp.toDouble()), pre)
        }


    }


}
