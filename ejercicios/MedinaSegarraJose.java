public static void bpArriba() {
		    System.out.print(ESC + "1J");
		    System.out.flush();
		}
		public static void bpAbajo() {
			System.out.print(ESC + "0J");
		    System.out.flush();
		    }
		public static void bl() {
		    System.out.print(ESC + "2K");
		    System.out.flush();
		    }
		public static void blDespues() {
		    System.out.print(ESC + "0K");
		    System.out.flush();
		    }
		public static void blAntes() {
		    System.out.print(ESC + "1K");
		    System.out.flush();
		    }
		public static void cursorXY(int columna, int linea) {
		    System.out.print(ESC + linea + ";" + columna + "H");
		    System.out.flush();
		    }

		public static void cursorArriba(int n) {
		    System.out.print(ESC + n + "A");
		    System.out.flush();
		    }

		public static void cursorAbajo(int n) {
		    System.out.print(ESC + n + "B");
		    System.out.flush();
		    }

		public static void cursorIzquierda(int n) {
		    System.out.print(ESC + n + "D");
		    System.out.flush();
		    }

		public static void cursorDerecha(int n) {
		    System.out.print(ESC + n + "C");
		    System.out.flush();
		    }

		public static void cursorInicio() {
		    System.out.print(ESC + "0G");
		    System.out.flush();
		    }
