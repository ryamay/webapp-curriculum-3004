object Interruption extends App {

  val t: Thread = new Thread(() => {
    try {
      while (true) {
        println("Sleeping...")
        Thread.sleep(1000)
      }
    } catch {
      case _: InterruptedException => "Interrupted!"
    }
  })
  t.start()

  Thread.sleep(10000)
  t.interrupt()
}
