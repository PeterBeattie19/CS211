compare = lambda s1, s2: s1<s2 if max(s1)==max(s2) else max(s1)<max(s2) 

            

def quicksort(x):
    if len(x) == 1 or len(x) == 0:
        return x

    else:
        pivot = x[0]
        i = 0
        for j in range(len(x)-1):
            if  compare(x[j+1],pivot):
                x[j+1],x[i+1] = x[i+1], x[j+1]
                i += 1

        x[0],x[i] = x[i],x[0] #swap

        first_part = quicksort(x[:i])
        second_part = quicksort(x[i+1:])
        first_part.append(x[i])
        return first_part + second_part


arr = raw_input().split()

arr = quicksort(arr)

sort = lambda s1,s2: max(s1) < max(s2) 


for i in arr:
    print i,


