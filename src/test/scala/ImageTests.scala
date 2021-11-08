package tests

import org.scalatest.funsuite.AnyFunSuite
import com.valleyprogramming.agt.imaging.get2dArraySegment

class ImageTestSuite extends AnyFunSuite {

    val sm_nrows = 3
    val sm_ncols = 4
    val sm_arr = Array.ofDim[Char](sm_nrows, sm_ncols)
    // row 1
    sm_arr(0)(0) = 'a'
    sm_arr(0)(1) = 'b'
    sm_arr(0)(2) = 'c'
    sm_arr(0)(3) = 'd'
    // row 2
    sm_arr(1)(0) = 'e'
    sm_arr(1)(1) = 'f'
    sm_arr(1)(2) = 'g'
    sm_arr(1)(3) = 'h'
    // row 3
    sm_arr(2)(0) = 'i'
    sm_arr(2)(1) = 'j'
    sm_arr(2)(2) = 'k'
    sm_arr(2)(3) = 'l'

    val lg_nrows = 6
    val lg_ncols = 7
    val lg_arr = Array.ofDim[Char](lg_nrows, lg_ncols)
    // row 1
    lg_arr(0)(0) = ' '
    lg_arr(0)(1) = ' '
    lg_arr(0)(2) = ' '
    lg_arr(0)(3) = ' '
    lg_arr(0)(4) = ' '
    lg_arr(0)(5) = ' '
    lg_arr(0)(6) = ' '
    // row 2
    lg_arr(1)(0) = ' '
    lg_arr(1)(1) = ' '
    lg_arr(1)(2) = ' '
    lg_arr(1)(3) = ' '
    lg_arr(1)(4) = ' '
    lg_arr(1)(5) = ' '
    lg_arr(1)(6) = ' '
    // row 3
    lg_arr(2)(0) = ' '
    lg_arr(2)(1) = ' '
    lg_arr(2)(2) = 'a'
    lg_arr(2)(3) = 'b'
    lg_arr(2)(4) = 'c'
    lg_arr(2)(5) = 'd'
    lg_arr(2)(6) = ' '
    // row 4
    lg_arr(3)(0) = ' '
    lg_arr(3)(1) = ' '
    lg_arr(3)(2) = 'e'
    lg_arr(3)(3) = 'f'
    lg_arr(3)(4) = 'g'
    lg_arr(3)(5) = 'h'
    lg_arr(3)(6) = ' '
    // row 5
    lg_arr(4)(0) = ' '
    lg_arr(4)(1) = ' '
    lg_arr(4)(2) = 'i'
    lg_arr(4)(3) = 'j'
    lg_arr(4)(4) = 'k'
    lg_arr(4)(5) = 'l'
    lg_arr(4)(6) = ' '
    // row 6
    lg_arr(5)(0) = ' '
    lg_arr(5)(1) = ' '
    lg_arr(5)(2) = ' '
    lg_arr(5)(3) = ' '
    lg_arr(5)(4) = ' '
    lg_arr(5)(5) = ' '
    lg_arr(5)(6) = ' '

    test("Test that i get correct chunk out of the large image array") {
        
        val lg_arr_curr_row = 0
        val lg_arr_curr_col = 0

        // get a 2d-array chunk from the lg_arr
        val lg_arr_2d_chunk = get2dArraySegment(
            lg_arr,
            lg_arr_curr_row,
            lg_arr_curr_col,
            sm_nrows, 
            sm_ncols
        )
        
        // the 2d array chunk should have 3 rows and 4 cols
        assert(lg_arr_2d_chunk.size == 3)
        assert(lg_arr_2d_chunk(0).size == 4)
        assert(lg_arr_2d_chunk(1).size == 4)
        assert(lg_arr_2d_chunk(2).size == 4)

    }



}






