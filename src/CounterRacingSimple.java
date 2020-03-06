
public class CounterRacingSimple {

	public static void main(String[] args) {
        // 1) �� ������� ������������� ������, �.�. � ��� ������ ��� ����������

		new CounterRacingSimple().doCounter();
	}
	
	private int count = 0;
	
	private synchronized void increment() {
		count++;
	}
	
	private void doCounter() {
        // 2) �� ��������� ��������� ����� ��������� �� ������ ����������
		// ���������������� ����� run, ��� ������������� � ������������ ������ Thread 
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
                // 3) ���� for ���������� �� 1000000 ��� ��� ������� �������, ����� - 2000000 ���
				// ��������� ���������� count
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
		// 4) � 5) ��� �������� �������� ������, count ���� ��� ���� ������� � ������������ ��������� Thread
		// � ����� ��������� ������������ � ������ �� �����.
		// ������ ��������� �������� ��� ������ ������������ ����, �� ������ ������ 2000000, �� ������, �� ��c�� ������ 1000000,
		// �.�. �� ������ ������ ���������� �� ������������� ����� (��� �� ������ t1, � ������ � t2)
		// count �������� ���������� �� ���� ������������ �����.
		t1.start();
		t2.start();
	}

}