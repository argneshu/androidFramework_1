package Functional_Scenarios;

class A
{
    static void staticMethod()
    {
        System.out.println("Static Method");
    }

 
//public class MainClass
//{
    public static void main(String[] args)
    {
    	//Integer i = new Integer(null);
    	int z=1;
    	System.out.println(++z);	
    	 int k=3;
    	System.out.println(k++ + k++);
    	 
       // String s = new String(null);
        A a = null;
 
        a.staticMethod();
        Integer i1 = 127;
        
        Integer i2 = 127;
 
        System.out.println(i1 == i2);
 
        Integer i3 = 128;
        System.out.println(i3);
 
        Integer i4 = 128;
        System.out.println(i4);
 
        System.out.println(i4 == i3);
    }
}
