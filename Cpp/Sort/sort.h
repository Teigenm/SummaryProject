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
	L.r[0]=L.r[low];     //用字表的第一个记录做枢纽记录
	pivotkey=L.r[low].key;  //记录枢纽关键字
	while(low<high)			
	{							//从表的两端交替地向中间扫描
		while(low<high&&L.r[high].key>=pivotkey) 
	{	--high;						//将比枢纽记录小的记录移动到低端
				
	}
		L.r[low].key=L.r[high].key;
		while(low<high&&L.r[low].key<=pivotkey)
		{	++low;					//将比枢纽记录大的记录移动到高端
			
		}
		L.r[high].key=L.r[low].key; 
		
	}
	L.r[low]=L.r[0];	//枢纽记录到位
	return low;			//返回枢纽所在位置
}
Status QSort(SqList &L,int low,int high)//快速排序
{	int pivotloc;
	if(low<high)
	{	pivotloc=Partition(L,low,high);   //将L.r[low..high]一分为二
		QSort(L,low,pivotloc-1);		//对低子表递归排序，pivotloc是枢纽所在位置
		QSort(L,pivotloc+1,high);		//对高子表递归排序
	}
	return OK;
}

void print(SqList &L)
{	int i;
	printf("\n");
	for(i=1;i<=L.length;i++)
	{	printf("%d ",L.r[i].key);   //输出表L中的值
	}
	printf("\n");
}


void Merge(SqList SR,SqList &TR,int i,int m,int n)
		//将有序的SR.r[i..m]和SR.r[m+1..n]归并为有序的TR[i..n]
{	int j,k;
	for(j=m+1,k=i;i<=m&&j<=n;++k)		//将SR中记录从小到大地并入TR
	{	if(SR.r[i].key<=SR.r[j].key)
		TR.r[k]=SR.r[i++];
		else
		TR.r[k]=SR.r[j++];

	}
	if(i<=m)
	{	for(;i<=m&&k<=n;i++,k++)		//将剩余的SR[i..m]复制到TR
		TR.r[k]=SR.r[i];
	}
	if(j<=n)
	{	for(;j<=n&&k<=n;k++,j++)		//将剩余的SR[j..n]复制到TR
		TR.r[k]=SR.r[j];
	}
}
void MSort(SqList SR,SqList &TR1,int low,int high)// 将SR.r[s..t]归并排序为TR1.r[s..t]
{	int m;
	SqList TR2;
	if(high==low)
	TR1.r[low]=SR.r[low];
	else
	{	m=(high+low)/2;	             //将SR.r[s..t]平分为SR.r[s..m]和SR.r[m+1..t]
		MSort(SR,TR2,low,m);		 //递归地将SR.r[s..m]归并为有序的TR2.r[s..m]
 		MSort(SR,TR2,m+1,high);		 //递归地将SR.r[m+1..t]归并为有序的TR2.r[m+1..t]
		Merge(TR2,TR1,low,m,high);	 //将TR2[s..m]和TR2[m+1..t]归并到TR1[s..t]

	}
}
void MergeSort(SqList &L)//归并排序
{	MSort(L,L,1,L.length);		     //对表L进行归并排序
}

void ShellInsert(SqList &L,int dk)
{	int i,j;
	for(i=dk+1;i<=L.length;i++)	
		if(L.r[i].key< L.r[i-dk].key)  //需将L.r[i]插入有序增量子表	
	{	L.r[0]=L.r[i];					//暂存在L.r[0]
		for(j=i-dk;j>0&&L.r[0].key<L.r[j].key;j-=dk)
		{	L.r[j+dk]=L.r[j];			//记录后移，查找插入位置
						
		}
		L.r[j+dk]=L.r[0];				//插入
	}
}

void ShellSort(SqList &L,int t)//希尔排序
{	int k;
	int dlta[200];
	for(k=0;k<t;k++)
	{dlta[k]=2*k+1;

	
	}
	for(k=0;k<t;k++)
	{	ShellInsert(L,dlta[k]);  //一趟增量为dlta[k]的插入排序
	}

}

void HeapAdjust(SqList &H,int s,int m)
{	int j;
	RedType rc;
	rc=H.r[s];

	for(j=2*s;j<=m;j=s*2)					//沿key较大的孩子结点向下筛选
	{	if(j<m&&H.r[j].key<H.r[j+1].key)	
			j++;							//j为key较大的记录的下标
		if(rc.key<H.r[j].key)				//rc插入在位置s上
			H.r[s]=H.r[j];			
		else
			break;
		s=j;
	}
	H.r[s]=rc;					//插入
}

void HeapSort(SqList &H)//堆排序
{	int i;
	int t;
	for(i=H.length/2;i>0;i--)		//把H.r[1..H.length]建成大顶堆
	{	HeapAdjust(H,i,H.length);   
	}
	for(i=H.length;i>1;i--)
	{	t=H.r[i].key;
		H.r[i].key=H.r[1].key;
		H.r[1].key=t;				//将堆顶记录和当前未经排序子序列H.r[1..i]中最后一个记录相互交换
		HeapAdjust(H,1,i-1);		//把H.r[1..i-1]重建成大顶堆
	}
}


Status Bubble(SqList &L)//冒泡排序
{
	int i,j,temp;
	for(i=1;i<L.length;i++)//冒泡次数
		for(j=i+1;j<=L.length;j++)//开始冒泡
		{
			if(L.r[j].key<L.r[i].key)
			{
				temp=L.r[i].key;
				L.r[i].key=L.r[j].key;
				L.r[j].key=temp;//依次冒泡将最小值排到最前面
			}
		}
		return OK;
}

Status Select(SqList &L)//简单选择排序
{
	int i,j,min,temp;
	for(i=0;i<L.length;i++)
	{
		temp=L.r[i].key;
		min=i;//记录第一个数的下标
		for(j=i+1;j<=L.length;j++)//记录最小值的下标
		{
			if(L.r[j].key<temp)
			{
				temp=L.r[j].key;
			    min=j; 
			}
		}
		if(i!=min)//如果i不等于min,则在后面找到了最小值
		{
		temp=L.r[i].key;
		L.r[i].key=L.r[min].key;
		L.r[min].key=temp;//将最小值排到最前面
		}
		
	}
			return OK;
}
Status  Insert(SqList &L)//直接插入排序
{
	int i,j;
	for(i=2;i<=L.length;i++)
	{
		if (L.r[i].key<L.r[i-1].key)  
		{
			L.r[0].key=L.r[i].key;//记录监视哨    
			for(j=i-1;L.r[j].key>L.r[0].key;j--) //比较然后再将记录往后移
				L.r[j+1].key=L.r[j].key;
			L.r[j+1].key=L.r[0].key;//插入
		}
	}
	return OK;
}