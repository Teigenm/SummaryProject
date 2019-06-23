//自己独立地完成了整个程序从分析、设计到编码的所有工作。
//如果在上述过程中，遇到了什么困难而求教于人，那么，将在实习总结报告中完整列举所遇到的问题，以及别人给的提示。
//程序里中凡是引用到其他程序或文档之处，例如教材、课堂笔记、网上的源代码以及其他参考书上的代码段，将在程序注释中清楚注明引用的出处。

#define MAXSIZE 10000


#include"sort.h"

Status InitSqList(SqList &L,clock_t size)
{	int i,number;
	L.length=MAXSIZE;
	srand(size);  //获得系统时间，以系统时间作为种子，得到随机函数。
	for(i=1;i<=L.length;i++)		
	{	number = rand() % 100001;		//得到的随机数赋值给number
		L.r[i].key=number;
		L.r[i].otherinfo=0;
	}
	return OK;
}

void main()
{	SqList L;
	clock_t size=(unsigned) time(NULL);
	FILETIME beg,end;
	int a;
	printf("随机生成的数：\n");

	printf("一共%d个数:\n",MAXSIZE);
	
	printf("1:快速排序\n2:堆排序\n3:归并排序\n4:希尔排序:\n5:冒泡排序\n6:简单选择排序\n7:直接插入排序\n\n输入0退出\n");
	printf("请输入相应数字进行对应的排序\n");
	scanf("%d",&a);
	long dir;
	
	while(a)
	{		
	switch(a)
	{
	case 1:	InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);/*获取程序开始的系统时间*/
			QSort(L,1,L.length);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);/*获取程序结束的系统时间*/
			//	print(L);
				dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n快速排序耗时为：%lf ms\n",dir/1000000.0);			
			break;

	case 2: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			HeapSort(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
			dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n堆排序耗时为：%lf ms\n",dir/1000000.0);		
			break;
	
	case 3:	InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			MergeSort(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
		dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n归并排序耗时为：%lf ms\n",dir/1000000.0);		
			break;
	
	case 4: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			ShellSort(L,3);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
			dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n希尔排序耗时为：%lf ms\n",dir/1000000.0);		
			break;
	case 5: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			Bubble(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
			dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n冒泡排序耗时为：%lf ms\n",dir/1000000.0);		
			break;

	case 6: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			Select(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
			dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n简单选择排序耗时为：%lf ms\n",dir/1000000.0);		
			break;
	case 7: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			Insert(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
		dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n直接插入排序耗时为：%lf ms\n",dir/1000000.0);		
			break;

	default: break;
	}
			printf("\n请继续输入相应数字\n");
			scanf("%d",&a);
	}
	printf("\n");

	
	
}