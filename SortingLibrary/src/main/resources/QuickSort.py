#read items from file and quicksort
def quicksort(integers, i, n):
    if(len(integers) > 1):
        p = partition(integers, i , len(integers)-1)
        if(p > 2):
            quicksort(integers, i, p-1)
        else:
            quicksort(integers, p+1, len(integers)-1)

def partition(integers, s, n):
    pivot = integers[s]
    i = s +1
    j = n
    while(i <= j):
        if(integers[i] < pivot):
            i = i + 1
        elif(integers[j] > pivot):
                j = j - 1
        else:
            temp = integers[j]
            integers[j] = integers[i]
            integers[i] = temp
            j = j - 1
            i = i + 1
    integers[s] = integers[j]
    integers[j] = pivot
    return j

file_integers = open('integers.csv','r')
integers = file_integers.readlines()
quicksort(integers, 1, len(integers))
print(integers)


