
public class CounterRacingSimple {

	public static void main(String[] args) {
        // 1) Мы создаем неименованный объект, т.к. в нем дальше нет надобности

		new CounterRacingSimple().doCounter();
	}
	
	private int count = 0;
	
	private synchronized void increment() {
		count++;
	}
	
	private void doCounter() {
        // 2) Мы ипользуем анонимный класс созданный на основе интерфейса
		// имплементирующий метод run, для использования в конструкторе класса Thread 
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
                // 3) Цикл for повторится по 1000000 раз для каждого объекта, всего - 2000000 раз
				// изменится переменная count
				for(int i = 0; i < 1_000_000; i++) {
					increment();
				}
                System.out.println("t1: " + count);
				
			}
		});

		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 1_000_000; i++) {
					increment();
				}
                System.out.println("t2: " + count);
				
			}
		});
		// 4) и 5) Это проблема задержки вывода, count один для двух потоков и необъяснимое поведение Thread
		// в плане алгоритма переключения с потока на поток.
		// Первое выводимое значение это первый отработавший трид, он всегда меньше 2000000, не меньше, но чаcто больше 1000000,
		// т.к. на момент вывода результата по отработавшему триду (это не всегда t1, а иногда и t2)
		// count успевает измениться за счет паралельного трида.
		t1.start();
		t2.start();
	}

}