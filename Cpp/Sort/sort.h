#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<windows.h>
#include<math.h>
#define OK 1
#define TURE 1
#define ERROR 0
#define OVERFLOW -2
typedef int Status;
typedef int KeyType;
typedef int InfoType;
typedef struct{
	KeyType key;
	InfoType otherinfo;
}RedType;
typedef struct{
	RedType r[MAXSIZE+1];
	int length;
}SqList;




Status Partition(SqList &L,int low,int high)
{	int pivotkey;
	L.r[0]=L.r[low];     //���ֱ�ĵ�һ����¼����Ŧ��¼
	pivotkey=L.r[low].key;  //��¼��Ŧ�ؼ���
	while(low<high)			
	{							//�ӱ�����˽�������м�ɨ��
		while(low<high&&L.r[high].key>=pivotkey) 
	{	--high;						//������Ŧ��¼С�ļ�¼�ƶ����Ͷ�
				
	}
		L.r[low].key=L.r[high].key;
		while(low<high&&L.r[low].key<=pivotkey)
		{	++low;					//������Ŧ��¼��ļ�¼�ƶ����߶�
			
		}
		L.r[high].key=L.r[low].key; 
		
	}
	L.r[low]=L.r[0];	//��Ŧ��¼��λ
	return low;			//������Ŧ����λ��
}
Status QSort(SqList &L,int low,int high)//��������
{	int pivotloc;
	if(low<high)
	{	pivotloc=Partition(L,low,high);   //��L.r[low..high]һ��Ϊ��
		QSort(L,low,pivotloc-1);		//�Ե��ӱ�ݹ�����pivotloc����Ŧ����λ��
		QSort(L,pivotloc+1,high);		//�Ը��ӱ�ݹ�����
	}
	return OK;
}

void print(SqList &L)
{	int i;
	printf("\n");
	for(i=1;i<=L.length;i++)
	{	printf("%d ",L.r[i].key);   //�����L�е�ֵ
	}
	printf("\n");
}


void Merge(SqList SR,SqList &TR,int i,int m,int n)
		//�������SR.r[i..m]��SR.r[m+1..n]�鲢Ϊ�����TR[i..n]
{	int j,k;
	for(j=m+1,k=i;i<=m&&j<=n;++k)		//��SR�м�¼��С����ز���TR
	{	if(SR.r[i].key<=SR.r[j].key)
		TR.r[k]=SR.r[i++];
		else
		TR.r[k]=SR.r[j++];

	}
	if(i<=m)
	{	for(;i<=m&&k<=n;i++,k++)		//��ʣ���SR[i..m]���Ƶ�TR
		TR.r[k]=SR.r[i];
	}
	if(j<=n)
	{	for(;j<=n&&k<=n;k++,j++)		//��ʣ���SR[j..n]���Ƶ�TR
		TR.r[k]=SR.r[j];
	}
}
void MSort(SqList SR,SqList &TR1,int low,int high)// ��SR.r[s..t]�鲢����ΪTR1.r[s..t]
{	int m;
	SqList TR2;
	if(high==low)
	TR1.r[low]=SR.r[low];
	else
	{	m=(high+low)/2;	             //��SR.r[s..t]ƽ��ΪSR.r[s..m]��SR.r[m+1..t]
		MSort(SR,TR2,low,m);		 //�ݹ�ؽ�SR.r[s..m]�鲢Ϊ�����TR2.r[s..m]
 		MSort(SR,TR2,m+1,high);		 //�ݹ�ؽ�SR.r[m+1..t]�鲢Ϊ�����TR2.r[m+1..t]
		Merge(TR2,TR1,low,m,high);	 //��TR2[s..m]��TR2[m+1..t]�鲢��TR1[s..t]

	}
}
void MergeSort(SqList &L)//�鲢����
{	MSort(L,L,1,L.length);		     //�Ա�L���й鲢����
}

void ShellInsert(SqList &L,int dk)
{	int i,j;
	for(i=dk+1;i<=L.length;i++)	
		if(L.r[i].key< L.r[i-dk].key)  //�轫L.r[i]�������������ӱ�	
	{	L.r[0]=L.r[i];					//�ݴ���L.r[0]
		for(j=i-dk;j>0&&L.r[0].key<L.r[j].key;j-=dk)
		{	L.r[j+dk]=L.r[j];			//��¼���ƣ����Ҳ���λ��
						
		}
		L.r[j+dk]=L.r[0];				//����
	}
}

void ShellSort(SqList &L,int t)//ϣ������
{	int k;
	int dlta[200];
	for(k=0;k<t;k++)
	{dlta[k]=2*k+1;

	
	}
	for(k=0;k<t;k++)
	{	ShellInsert(L,dlta[k]);  //һ������Ϊdlta[k]�Ĳ�������
	}

}

void HeapAdjust(SqList &H,int s,int m)
{	int j;
	RedType rc;
	rc=H.r[s];

	for(j=2*s;j<=m;j=s*2)					//��key�ϴ�ĺ��ӽ������ɸѡ
	{	if(j<m&&H.r[j].key<H.r[j+1].key)	
			j++;							//jΪkey�ϴ�ļ�¼���±�
		if(rc.key<H.r[j].key)				//rc������λ��s��
			H.r[s]=H.r[j];			
		else
			break;
		s=j;
	}
	H.r[s]=rc;					//����
}

void HeapSort(SqList &H)//������
{	int i;
	int t;
	for(i=H.length/2;i>0;i--)		//��H.r[1..H.length]���ɴ󶥶�
	{	HeapAdjust(H,i,H.length);   
	}
	for(i=H.length;i>1;i--)
	{	t=H.r[i].key;
		H.r[i].key=H.r[1].key;
		H.r[1].key=t;				//���Ѷ���¼�͵�ǰδ������������H.r[1..i]�����һ����¼�໥����
		HeapAdjust(H,1,i-1);		//��H.r[1..i-1]�ؽ��ɴ󶥶�
	}
}


Status Bubble(SqList &L)//ð������
{
	int i,j,temp;
	for(i=1;i<L.length;i++)//ð�ݴ���
		for(j=i+1;j<=L.length;j++)//��ʼð��
		{
			if(L.r[j].key<L.r[i].key)
			{
				temp=L.r[i].key;
				L.r[i].key=L.r[j].key;
				L.r[j].key=temp;//����ð�ݽ���Сֵ�ŵ���ǰ��
			}
		}
		return OK;
}

Status Select(SqList &L)//��ѡ������
{
	int i,j,min,temp;
	for(i=0;i<L.length;i++)
	{
		temp=L.r[i].key;
		min=i;//��¼��һ�������±�
		for(j=i+1;j<=L.length;j++)//��¼��Сֵ���±�
		{
			if(L.r[j].key<temp)
			{
				temp=L.r[j].key;
			    min=j; 
			}
		}
		if(i!=min)//���i������min,���ں����ҵ�����Сֵ
		{
		temp=L.r[i].key;
		L.r[i].key=L.r[min].key;
		L.r[min].key=temp;//����Сֵ�ŵ���ǰ��
		}
		
	}
			return OK;
}
Status  Insert(SqList &L)//ֱ�Ӳ�������
{
	int i,j;
	for(i=2;i<=L.length;i++)
	{
		if (L.r[i].key<L.r[i-1].key)  
		{
			L.r[0].key=L.r[i].key;//��¼������    
			for(j=i-1;L.r[j].key>L.r[0].key;j--) //�Ƚ�Ȼ���ٽ���¼������
				L.r[j+1].key=L.r[j].key;
			L.r[j+1].key=L.r[0].key;//����
		}
	}
	return OK;
}