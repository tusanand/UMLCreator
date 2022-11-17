
public abstract class LineDecorator implements ConnectClassInterface {
	private ConnectClassInterface connectClassInterface;
	
	public void decorate(ConnectClassInterface connectClassInterface) {
		this.connectClassInterface = connectClassInterface;
	}
	
	@Override
	public void draw(int x1, int y1, int x2, int y2) {
		if(connectClassInterface != null) {
			connectClassInterface.draw(x1, y1, x2, y2);
		}
	}
}
