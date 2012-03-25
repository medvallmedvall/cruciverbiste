package entities;


public class MotGrille {
        private int idGrille;
        private int orientation;
        private Mot mot;
        private Definition definition;
        private int coordX;
        private int coordY;
        private int orderId;

        public MotGrille() {
        }
        
        public MotGrille(int idGrille, int orientation, Mot mot,
                        Definition definition, int x, int y) {
                this.idGrille = idGrille;
                this.orientation = orientation;
                this.mot = mot;
                this.definition = definition;
                this.coordX = x;
                this.coordY = y;
        }

        public int getIdGrille() {
                return this.idGrille;
        }

        public void setId(int id) {
                this.idGrille = id;
        }

        public int getOrientation() {
                return this.orientation;
        }

        public void setOrientationmots(int orientation) {
                this.orientation = orientation;
        }

        public int getCoordX() {
                return this.coordX;
        }

        public void setCoordX(int coordX) {
                this.coordX = coordX;
        }

        public int getCoordY() {
                return this.coordY;
        }

        public void setCoordY(int coordY) {
                this.coordY = coordY;
        }
        
        public void setIdGrille(int idGrille) {
                this.idGrille = idGrille;
        }

        public void setOrientation(int orientation) {
                this.orientation = orientation;
        }

        public void setMot(Mot mot) {
                this.mot = mot;
        }

        public void setDefinition(Definition definition) {
                this.definition = definition;
        }
        
        public Mot getMotObj() {
        	return mot;
        }
        public String getMot() {
                return mot.getMot();
        }
        
        public String getDefinition() {
                return definition.getDefinition();
        }
        
        public int getIdDefinition() {
            return definition.getIdDefinition();
        }
          
        public String getSynonyme() {
        	if (mot.getSynonymes().isEmpty()) {
        		return "";
        	}
        	return mot.getSynonymes().get(0);
        }

		public int getOrderId() {
			return orderId;
		}
		
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
}

