import java.awt.*;

    public abstract class GameObject {
        int x;
        int y;
        int vx;
        int vy;
        int angle;
        int height;
        int width;

        Rectangle my_rectangle;

        void setX(int x_to_set) {
            this.x = x_to_set;
        }

        int getX() {
            return this.x;
        }

        void setY(int y_to_set) {
            this.y = y_to_set;
        }

        int getY() {
            return this.y;
        }

        int getVx() {
            return vx;
        }

        void setVx(int vx) {
            this.vx = vx;
        }

        int getVy() {
            return vy;
        }

        void setVy(int vy) {
            this.vy = vy;
        }

        int getAngle() {
            return angle;
        }

        void setAngle(int angle) {
            this.angle = angle;
        }

        public abstract void update();

        public abstract void drawImage(Graphics2D g);

        public abstract void collision();

    }

