package tests

import org.scalatest.funsuite.AnyFunSuite
import com.valleyprogramming.agt.system.exec

/**
 * A series of tests of the AGT `system.exec` method.
 */
class ExecTestSuite extends AnyFunSuite {

    test("Test 'ls -al' returns status code == 0") {
        val (status, stdout, stderr) = exec("ls -al")
        assert(status == 0)
        assert(stdout.trim.length > 0)
        assert(stderr.trim.length == 0)
    }

    test("Test 'ls -al MeWantCookie' returns status code == 1") {
        val (status, stdout, stderr) = exec("cd /tmp; ls -al MeWantCookie")
        assert(status == 1)
        assert(stdout.trim.length == 0)
        assert(stderr.trim.length > 0)
    }

    test("Test that 'cat /etc/passwd' works") {
        val (status, stdout, stderr) = exec("cat /etc/passwd")
        assert(status == 0)
        assert(stdout.length > 100)
        assert(stderr.trim.length == 0)
    }

    test("Test that pipe using '/etc/passwd' works") {
        val (status, stdout, stderr) = exec("wc -l /etc/passwd | tr -s ' ' | cut -d' ' -f2")
        assert(status == 0)
        assert(stdout.trim.toInt > 50)
        assert(stderr.trim.length == 0)
    }

}
