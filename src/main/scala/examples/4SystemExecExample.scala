package tests

import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.system.*

@main def systemExecExample =

    "create a series of system commands".c
    val cmds = Seq(
        "ls -al",                      // status=0, stdout
        "ls -al cookie",               // status=1, stderr='ls: cookie: No such file or directory'
        "cat /etc/passwd",             // status=0, stdout
        "wc -l /etc/passwd | tr -s ' ' | cut -d' ' -f2",   // status: 0, stdout: 120, stderr: ''
        "cd ~; ls -1 | grep SOURCE"    // uses ; and |
    )

    "run each of the system commands".c
    for cmd <- cmds do
        println(s"\nCMD: $cmd")
        val (status, stdout, stderr) = exec(cmd)
        println(s"status: $status")
        println(s"stdout: $stdout")
        println(s"stderr: $stderr")

    writeComments

