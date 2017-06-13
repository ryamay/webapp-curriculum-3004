import java.util.concurrent.CopyOnWriteArrayList

object QueueWithSemaphore extends App {

  val arrayList = new CopyOnWriteArrayList[Runnable]()

  for (i <- 1 to 100) {
    arrayList.add(() => {
      Thread.sleep(1000)
      println(s"Runnable: ${i} finished.")
    })
  }

  for (i <- 1 to 20) {
    val t = new Thread(() => {
      while (true) {
        val runnable = arrayList.remove(0)
        runnable.run()
      }
    })
    t.start()
  }
}