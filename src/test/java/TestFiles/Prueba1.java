package TestFiles;

public class Prueba1 {
	public class Clase1 {
		int a, b, c;

		public void methodA() { //CC = 4
			if (a == 0 || b == 0 || c == 0) {
				System.out.println("Hay cero");
			} else {
				System.out.println("No h}ay cero");
			}
		}

		public void methodB() { //CC = 5
			switch (a) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:
				break;
			//default:
			//	break;
			}
		}

		public void methodC() { //CC = 2
			switch (b) {
			case 0:
				
				break;

			default:
				break;
			}

		}
	}

	public class Clase2 {
		int a,b,c;
		public void methodA() { //CC = 2
			for (int i = 0; i < 5; i++) {
				
			}
			
		}

		public void methodB() { //CC = 4
			do {
				while (a==0) {
					if (b==0) {
						
					}
				}
			} while (c==0);
		}

	}

}