Program gauss;

Var sum, upper, i: Integer;
    
Begin
    In("Number? ", upper);
    i := 1;
    While i <= upper Do
    Begin
        sum := sum + i;
        i := i+1
    End;
    Out("Sum: ", sum);
    Out("Which equals ", upper * (upper+1) / 2)
End.
