package networking;

import java.util.Scanner;


class crc_compute {
	private int dividend_count,divisor_count,code_count;
	private int[] dataword ;
	private int []generator;
	

	crc_compute(int[] dword,int[] gtor){
		code_count=dword.length+gtor.length-1;
		dataword=new int[code_count];
		generator=new int[gtor.length];
		dataword=new int[code_count];
		for(int i=0;i<dword.length;i++)
		{
			dataword[i]=dword[i];
		}
		for(int j=0;j<gtor.length;j++)
		{
			generator[j]=gtor[j];
		}
		dividend_count=dword.length;
		divisor_count=gtor.length;
	}


	public int[] generateDataword()
	{
		//appending 0's to dataword
		for(int i=dividend_count;i<code_count;i++)
		{
			dataword[i]=0;
		}
		int div[]=new int[divisor_count];
		int dis[]=new int[divisor_count];
		for(int i=0;i<divisor_count;i++)
		{
			div[i]=dataword[i];
			dis[i]=generator[i];
		}

		int T=0;
		for(int i=1;i<divisor_count;i++)
		{
			div[i-1]=div[i]^dis[i];
		}

		while(T++<code_count-divisor_count)
		{
			div[divisor_count-1]=dataword[divisor_count+T-1];
			if(div[0]==0)
			{
				for(int i=1;i<divisor_count;i++)
				{
					div[i-1]=div[i]^0;
				}
			}

			else
			{
				for(int i=1;i<divisor_count;i++)
				{
					div[i-1]=div[i]^dis[i];
				}
			}
		}

		int k=0;
		for(int j=dividend_count;j<code_count;j++)
		{
			dataword[j]=div[k];
			k++;
		}
		return dataword;
	}
}
public class crc
{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no.of bits in dataword");
		int data_count=sc.nextInt();
		int dataword[]=new int[data_count];
		System.out.println("Enter datword");
		for(int i=0;i<data_count;i++)
		{
			dataword[i]=sc.nextInt();
		}
		System.out.println("Enter no.of bits in generator");
		int gen_count=sc.nextInt();
		int generator[]=new int[gen_count];
		System.out.println("Enter generator");
		for(int i=0;i<gen_count;i++)
		{
			generator[i]=sc.nextInt();
		}
		crc_compute c=new crc_compute(dataword,generator);
		int codeword[]=c.generateDataword();
		System.out.print("Codeword: ");
		for(int i=0;i<codeword.length;i++)
		{
			System.out.print(codeword[i]);
		}
		System.out.println();
		int code_count=codeword.length;
		System.out.println("Enter the codeword received");
		int received[]=new int[code_count];
		for(int i=0;i<code_count;i++)
		{
			received[i]=sc.nextInt();
		}
		for(int i=0;i<code_count;i++)
		{
			if(received[i]!=codeword[i])
			{
				System.out.println("Error found");
				System.exit(1);
			}
		}
		System.out.println("No errors detected.Dataword accepted");
	}
}
