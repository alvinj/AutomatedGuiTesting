package com.valleyprogramming.agt

import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import com.valleyprogramming.agt.Point
import util.control.Breaks.*
import com.valleyprogramming.agt.main.sleep


/**
 * This code is used for all “imaging” work. The primary purpose is to be able
 * to compare a known image -- such as the Apple News icon -- to the current 
 * screen, using the `findSmallImageInLargeImage` method. For example, `imageTest2`
 * shows how to do exactly that, and assuming that the macOS Simulator is
 * currently running and on its home screen, `imageTest2` shows how to find
 * the points on the screen where the Apple News icon is found.
 *
 * As a note, only the “mid point” is really necessary, because that’s where you
 * want to click on the icon, but I currently return the other information for
 * debugging purposes.
 */
object imaging:

    val DEBUG_MODE = false

    def debug(s: String): Unit =
        if DEBUG_MODE then println(s)


    @main def imageTest1 =
        val lgImage: BufferedImage = ImageIO.read(File("/Users/al/Desktop/lg-image.png"))
        val lgImageRows = lgImage.getHeight
        val lgImageCols = lgImage.getWidth

        val smImage: BufferedImage = ImageIO.read(File("/Users/al/Desktop/sm-image.png"))
        val smImageRows = smImage.getHeight
        val smImageCols = smImage.getWidth

        debug(s"LARGE(w/h): $lgImageCols x $lgImageRows")
        debug(s"SMALL(w/h): $smImageCols x $smImageRows")
        
        // Array.ofDim[String](rows, cols)
        val smImgArr = Array.ofDim[Color](smImageRows, smImageCols)
        val lgImgArr = Array.ofDim[Color](lgImageRows, lgImageCols)
        
        debug(s"small image size: ${smImgArr(0).length} cols X ${smImgArr.length} rows")
        debug(s"large image size: ${lgImgArr(0).length} cols X ${lgImgArr.length} rows")

        populate2DArray(smImage, smImgArr, smImageRows, smImageCols)
        populate2DArray(lgImage, lgImgArr, lgImageRows, lgImageCols)
        
        // expect width = 375, height = 187
        val opt: Option[(Point, Point, Point)] = findSmallImageInLargeImage(
            smImgArr,
            smImageRows,
            smImageCols,
            lgImgArr,
            lgImageRows,
            lgImageCols
        )

        opt match
            case Some(tuple3) =>
                // TODO handle this better
                println(s"UL-POINT:  ${tuple3(0)}")
                println(s"LR-POINT:  ${tuple3(1)}")
                println(s"MID_POINT: ${tuple3(2)}")
            case None =>
                println("Did not find a match")

    end imageTest1


    @main def imageTest2 =
    
        // EXPECTED ANSWER:
        // UL-POINT:  Point(416,1080)   (I measure 415 x 1076 in the screenshot)
        // LR-POINT:  Point(452,1116)
        // MID_POINT: Point(434,1098)
        // This worked when I made the small image (icon) from the large image.
        // That is, I created the Large Screenshot with the Simulator running.
        // Then I copied that image, and cropped the small Apple News Icon out
        // of that Large Screenshot. Then this worked as desired.
    
        println(s"\n***** bring the Simulator to the foreground *****")
        sleep(5_000)
    
        println(s"\n***** taking the screenshot *****")
        sleep(5_000)

        // create the screenshot and save it as a PNG file
        val lgImageScreenshot: BufferedImage = createScreenShot
        val lgImageFilename = "/Users/al/Desktop/screenshot.png"
        ImageIO.write(lgImageScreenshot, "PNG", File(lgImageFilename))

        // read that image back in
        val lgImage: BufferedImage = ImageIO.read(File(lgImageFilename))
        val lgImageRows = lgImage.getHeight
        val lgImageCols = lgImage.getWidth

        val smImage: BufferedImage = ImageIO.read(File("/Users/al/Desktop/apple-news-icon.png"))
        val smImageRows = smImage.getHeight
        val smImageCols = smImage.getWidth

        debug(s"LARGE(w/h): $lgImageCols x $lgImageRows")
        debug(s"SMALL(w/h): $smImageCols x $smImageRows")
        
        // Array.ofDim[String](rows, cols)
        val smImgArr = Array.ofDim[Color](smImageRows, smImageCols)
        val lgImgArr = Array.ofDim[Color](lgImageRows, lgImageCols)
        
        debug(s"small image size: ${smImgArr(0).length} cols X ${smImgArr.length} rows")
        debug(s"large image size: ${lgImgArr(0).length} cols X ${lgImgArr.length} rows")

        populate2DArray(smImage, smImgArr, smImageRows, smImageCols)
        populate2DArray(lgImage, lgImgArr, lgImageRows, lgImageCols)
        
        // expect width = 375, height = 187
        val opt: Option[(Point, Point, Point)] = findSmallImageInLargeImage(
            smImgArr,
            smImageRows,
            smImageCols,
            lgImgArr,
            lgImageRows,
            lgImageCols
        )

        opt match
            case Some(tuple3) =>
                // TODO handle this better
                println(s"UL-POINT:  ${tuple3(0)}")
                println(s"LR-POINT:  ${tuple3(1)}")
                println(s"MID_POINT: ${tuple3(2)}")
            case None =>
                println("Did not find a match")

    end imageTest2


    def createScreenShot: BufferedImage =
        import java.awt.Robot
        import java.awt.Toolkit
        import java.awt.Dimension
        import java.awt.Rectangle
        import java.awt.image.BufferedImage
        import javax.imageio.ImageIO
        val robot = Robot()
        val toolkit = Toolkit.getDefaultToolkit
        val dim = toolkit.getScreenSize
        val rectangle = Rectangle(0, 0, dim.width, dim.height)
        val screenCapture = robot.createScreenCapture(rectangle)
        screenCapture
    end createScreenShot
    
    
    def findMidPointOfSmallImageInLargeImage(
        smImgArr: Array[Array[Color]],
        smImgRows: Int,
        smImgCols: Int,
        lgImgArr: Array[Array[Color]],
        lgImgRows: Int,
        lgImgCols: Int
    ): Option[Point] = 
        val tuple3Opt = findSmallImageInLargeImage(
            smImgArr,
            smImgRows,
            smImgCols,
            lgImgArr,
            lgImgRows,
            lgImgCols
        )
        tuple3Opt.map(tup => tup._3)

    // TODO: add unit tests.
    def findSmallImageInLargeImage(
        smImgArr: Array[Array[Color]],
        smImgRows: Int,
        smImgCols: Int,
        lgImgArr: Array[Array[Color]],
        lgImgRows: Int,
        lgImgCols: Int
    ): Option[(Point, Point, Point)] =

        // start with some bookkeeping. these variables will keep the row/col coordinates of where
        // the small image is found inside the large image. they are the row/col of the large image.
        // var lg_img_arr_ul_col = 0
        // var lg_img_arr_ul_row = 0

        val lg_nrows_to_get = lgImgRows - smImgRows
        for lg_curr_row <- 0 to lg_nrows_to_get do
            debug(s"lg_curr_row = $lg_curr_row")

            // within the 1st row, start at the 1st column, then the 2nd col, ...
            val lg_ncols_to_get = lgImgCols - smImgCols
            for lg_curr_col <- 0 to lg_ncols_to_get do
                // debug(s"lg_curr_col = $lg_curr_col")
                if lg_curr_row % 50 == 0 then
                    debug(s"looking at lgImgArr($lg_curr_row)($lg_curr_col)")

                // NOTE: the code is orders of magnitude faster by first doing this little test.
                // There is probably a *lot* of optimization work that can be done with my
                // array-manipulation code.
                // Do a quick test here before spending time on the following array-comparison work.
                // Compare smImgArr(0) to the current row of the lgImgArr.
                val lgImgArrRowSeqment = getLargeImageArraySegment(lgImgArr, lg_curr_row, lg_curr_col, smImgCols)
                if rowsAreEqual(smImgArr(0), lgImgArrRowSeqment) then
                    // if you’re in here, that means the quick comparison of the first row
                    // of the sm-img-arr (a 1D array) and the 1D segment of the lg-img-arr worked. 
                    // This is a good start, so now do the rest of the work to fully compare the 
                    // two 2D arrays.

                    val lg_arr_2d_chunk = get2dArraySegment(
                        lgImgArr,
                        lg_curr_row,
                        lg_curr_col,
                        smImgRows, 
                        smImgCols
                    )

                    if arraysAreEqual(lg_arr_2d_chunk, smImgArr) then
                        // the problem is solved, do the bookkeeping
                        val ulPoint = Point(lg_curr_row, lg_curr_col)
                        val brPoint = Point(
                            lg_curr_row + smImgRows,
                            lg_curr_col + smImgCols
                        )
                        val midPoint = Point(
                            ((ulPoint.x + brPoint.x)/2.0).toInt,
                            ((ulPoint.y + brPoint.y)/2.0).toInt
                        )
                        return Some(ulPoint, brPoint, midPoint)
                    end if
                end if  // quick rowsAreEqual test

            end for
        end for
        
        return None

    end findSmallImageInLargeImage


    /**
     * Get a 1D segment of the lg-img-arr 2D array that is the same size as one row
     * of the sm-img-arr.
     */
    // TODO: add unit tests.
    def getLargeImageArraySegment(
        lgImgArr: Array[Array[Color]],
        currRow: Int,
        currCol: Int,
        nColsToGet: Int
    ): Array[Color] =
        val lgImgArrRow = lgImgArr(currRow)
        val lgImgArrRowSeqment = lgImgArrRow.slice(currCol, nColsToGet+currCol)
        lgImgArrRowSeqment


    // TODO: there are probably much better ways to do this.
    // TODO: add unit tests.
    def get2dArraySegment(
        in2dArr: Array[Array[Color]],
        currInRow: Int,
        currInCol: Int,
        nRowsToGet: Int,
        nColsToGet: Int
    ): Array[Array[Color]] =

        debug(s"lg-arr-segment to extract: $nRowsToGet rows, $nColsToGet cols")
        // you know the size of the desired 2d array, so create it:
        val out2dArr = Array.ofDim[Color](nRowsToGet, nColsToGet)

        // need this for bookkeeping below:
        var outRowCount = 0

        debug(s"currInRow  = $currInRow")
        debug(s"currInCol  = $currInCol")
        debug(s"nRowsToGet = $nRowsToGet")
        debug(s"nColsToGet = $nColsToGet")

        // from 0 to 2 do ...
        val lastRowInInArr = currInRow + nRowsToGet -1
        for row <- currInRow to lastRowInInArr do

            debug(s"row = $row")

            // get a full row out of in2dArr
            val in_arr_row = in2dArr(row)
            debug(s"in_arr_row.length = ${in_arr_row.length} (should be length of lg-arr)")
        
            // convert that to a segment of the correct length
            val in_arr_row_segment = in_arr_row.slice(currInCol, nColsToGet+currInCol)
            debug(s"in_arr_row_segment.length = ${in_arr_row_segment.size} (should be length of sm-arr)")

            // you have a row segment. copy that to the outgoing array.
            for col <- 0 to nColsToGet-1 do
                // debug(s"col: $col")
                out2dArr(outRowCount)(col) = in_arr_row_segment(col)

            // now get the next row
            outRowCount += 1
        end for
        out2dArr
    end get2dArraySegment


    // TODO: there are probably much better ways to do this.
    // TODO: add unit tests.
    def arraysAreEqual(arr1: Array[Array[Color]], arr2: Array[Array[Color]]): Boolean =
        debug(s"\nENTERED arraysAreEqual")
        val nrows1 = arr1.length
        val nrows2 = arr2.length
        debug(s"    num rows to compare = $nrows1")
        if nrows1 != nrows2 then
            debug(s"    yikes, row count did not match")
            false
        else
            // they have the same number of rows, so test each row
            for r <- 0 to nrows1-1 do
                debug(s"    row 'r' = $r")
                if rowsAreEqual(arr1(r), arr2(r)) then
                    debug(s"    rows were equal")
                else 
                    debug(s"    rows were NOT equal, leaving arraysAreEqual")
                    return false
                end if
            end for
            // come here if all the rows are equal, so:
            debug(s"    the arrays were equal")
            return true
        end if
    end arraysAreEqual


    // TODO: there are probably much better ways to do this.
    // TODO: add unit tests.
    // This method seems to work.
    // val r1 = Array( Color(1,1,1,1), Color(2,2,2,2), Color(3,3,3,3) )
    // val r2 = Array( Color(1,1,1,1), Color(2,2,2,2) )
    // val r3 = Array( Color(1,1,1,1), Color(2,2,2,2), Color(3,3,3,3) )
    // rowsAreEqual(r1, r2)   // false
    // rowsAreEqual(r1, r3)   // true
    def rowsAreEqual(arr1: Array[Color], arr2: Array[Color]): Boolean = 
        debug(s"\nENTERED rowsAreEqual")
        val rez: Boolean = arr1.corresponds(arr2){ almostEqual(_, _) }
        debug(s"    rowsAreEqual = $rez")
        rez
    end rowsAreEqual
    
    
    // TODO: add unit tests.
    def almostEqual(c1: Color, c2: Color): Boolean = 
        val tol = 5
        val alphaDiff = Math.abs(c1.alpha - c2.alpha)
        val redDiff   = Math.abs(c1.red   - c2.red)
        val greenDiff = Math.abs(c1.green - c2.green)
        val blueDiff  = Math.abs(c1.blue  - c2.blue)
        if alphaDiff < tol && redDiff < tol && greenDiff < tol && blueDiff < tol then
            true
        else
            false


    // this method seems to work
    def populate2DArray(
        image: BufferedImage,
        arr: Array[Array[Color]],
        nrows: Int,
        ncols: Int,
    ): Unit =
        for r <- 0 to nrows-1 do
            for c <- 0 to ncols-1 do
                val pixel: Int = image.getRGB(c, r)
                arr(r)(c) = getPixelARGB(pixel)
                // debug(s"arr(r)(c) = ${arr(r)(c)}")
    

    // def marchThroughImage(image: BufferedImage): Unit =
    //     val width: Int = image.getWidth
    //     val height: Int = image.getHeight
    //     debug(s"width, height: $width, $height")
    //         for h <- 0 to height-1 do
    //             for w <- 0 to width-1 do
    //                 // debug(s"w, h: $w, $h")
    //                 val pixel: Int = image.getRGB(w, h)
    //                 debug(getPixelARGB(pixel))
    // end marchThroughImage


    case class Color(
        alpha: Int,
        red: Int,
        green: Int,
        blue: Int
    ):
        override def toString = s"argb: $alpha, $red, $green, $blue"


    def getPixelARGB(pixel: Int): Color =
        val alpha: Int = (pixel >> 24) & 0xff
        val red: Int = (pixel >> 16) & 0xff
        val green: Int = (pixel >> 8) & 0xff
        val blue: Int = (pixel) & 0xff
        Color(alpha, red, green, blue)


    def printPixelARGB(pixel: Int): Unit =
        val alpha: Int = (pixel >> 24) & 0xff
        val red: Int = (pixel >> 16) & 0xff
        val green: Int = (pixel >> 8) & 0xff
        val blue: Int = (pixel) & 0xff
        // debug(s"argb: $alpha, $red, $green, $blue")

end imaging
