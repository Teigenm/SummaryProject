//�Լ��������������������ӷ�������Ƶ���������й�����
//��������������У�������ʲô���Ѷ�������ˣ���ô������ʵϰ�ܽᱨ���������о������������⣬�Լ����˸�����ʾ��
//�������з������õ�����������ĵ�֮��������̲ġ����ñʼǡ����ϵ�Դ�����Լ������ο����ϵĴ���Σ����ڳ���ע�������ע�����õĳ�����

#define MAXSIZE 10000


#include"sort.h"

Status InitSqList(SqList &L,clock_t size)
{	int i,number;
	L.length=MAXSIZE;
	srand(size);  //���ϵͳʱ�䣬��ϵͳʱ����Ϊ���ӣ��õ����������
	for(i=1;i<=L.length;i++)		
	{	number = rand() % 100001;		//�õ����������ֵ��number
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
	printf("������ɵ�����\n");

	printf("һ��%d����:\n",MAXSIZE);
	
	printf("1:��������\n2:������\n3:�鲢����\n4:ϣ������:\n5:ð������\n6:��ѡ������\n7:ֱ�Ӳ�������\n\n����0�˳�\n");
	printf("��������Ӧ���ֽ��ж�Ӧ������\n");
	scanf("%d",&a);
	long dir;
	
	while(a)
	{		
	switch(a)
	{
	case 1:	InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);/*��ȡ����ʼ��ϵͳʱ��*/
			QSort(L,1,L.length);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);/*��ȡ���������ϵͳʱ��*/
			//	print(L);
				dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n���������ʱΪ��%lf ms\n",dir/1000000.0);			
			break;

	case 2: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			HeapSort(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
			dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n�������ʱΪ��%lf ms\n",dir/1000000.0);		
			break;
	
	case 3:	InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			MergeSort(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
		dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n�鲢�����ʱΪ��%lf ms\n",dir/1000000.0);		
			break;
	
	case 4: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			ShellSort(L,3);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
			dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\nϣ�������ʱΪ��%lf ms\n",dir/1000000.0);		
			break;
	case 5: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			Bubble(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
			dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\nð�������ʱΪ��%lf ms\n",dir/1000000.0);		
			break;

	case 6: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			Select(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
			dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\n��ѡ�������ʱΪ��%lf ms\n",dir/1000000.0);		
			break;
	case 7: InitSqList(L,size);
			//	print(L);
			GetSystemTimeAsFileTime(&beg);
			Insert(L);
			Sleep(1000);
			GetSystemTimeAsFileTime(&end);
			//	print(L);
		dir=100*(end.dwLowDateTime-beg.dwLowDateTime)-1000000000;
			printf("\nֱ�Ӳ��������ʱΪ��%lf ms\n",dir/1000000.0);		
			break;

	default: break;
	}
			printf("\n�����������Ӧ����\n");
			scanf("%d",&a);
	}
	printf("\n");

	
	
}