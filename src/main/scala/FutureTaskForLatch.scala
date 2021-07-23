import java.util.concurrent.FutureTask

object FutureTaskForLatch extends App {

  val futureTasks = for (i <- 1 to 3)
    yield new FutureTask[Int](() => {
      Thread.sleep(1000)
      println(s"FutureTask ${i} finished")
      i
    })
  futureTasks.foreach((f) => new Thread(f).start())

  new Thread({ () =>
    println("waiting...")
    await
    println("START!!")
  }).start()

  def await = {
    futureTasks.foreach(f => f.get())
  }
}
