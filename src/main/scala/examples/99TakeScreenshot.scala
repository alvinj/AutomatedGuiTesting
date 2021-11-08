package tests

import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.imaging.*
import java.awt.image.BufferedImage
import java.io.{File, IOException}
import javax.imageio.ImageIO

@main def takeScreenshot99 =

    val imageFilename = "/Users/al/Desktop/screenshot1.png"
    val sleepTime = 8_000

    println(s"Sleeping for $sleepTime ms you can arrange the screen as desired ...")
    sleep(sleepTime)

    println(s"Taking the screenshot now.")
    val lgImageScreenshot: BufferedImage = createScreenShot
    ImageIO.write(lgImageScreenshot, "PNG", File(imageFilename))

    println(s"Saving the image as '$imageFilename'.")
    println(s"Done.")
