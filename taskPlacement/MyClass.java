import java.io.Serializable;
 
class MyClass implements Serializable {
    String s;
    int i;
    double d;
 
	double myValue;

    public MyClass(String s, int i, double d) {
        this.s = s;
        this.i = i;
        this.d = d;
		setMyValue();
    }
	
	public void setName(String s){
		this.s = s;
	}

	private void setMyValue(){
		myValue = d*2;
	}

	public double getMyValue(){
		return myValue;
	}
 
    @Override
    public String toString() {
        return "s=" + s + ";   i=" + i + ";   d=" + d;
    }
}

