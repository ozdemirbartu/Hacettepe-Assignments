// Assignment1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <fstream>
#include <vector>
#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <iostream>
#include <stack>
#include <cstring>
using namespace std;
int** grid;
int** grid2;
bool** vis;



int prow, pcol;
int fileIndex = 0;
int linkCount = 0;
int totalPoint = 0;
// Initialize direction vectors
int dRow[] = { 0, 1, 0, -1 };
int dCol[] = { -1, 0, 1, 0 };


void printGrid(int size, string outputFile){ //prints the grid
    //ofstream outFile(outputFile,std::ios_base::app); //outputfile
    ofstream outFile;
    outFile.open(outputFile.c_str(), std::ios_base::app);
    
    if (fileIndex==0)//prints the grid for input1
    {
        outFile << "PART 1:"<<endl;
        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                outFile << grid[i][j] << ' ';
            }
            outFile << endl;
        }

    }
    if (fileIndex == 1)//prints the grid for input2
    {
        outFile << "PART 2:"<<endl;
        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                outFile << grid2[i][j] << ' ';
            }
            outFile << endl;
        }
        outFile << "Final Point: " << totalPoint << "p";
    }
    outFile.close();

}

bool isValid(int size, int row, int col)
{
    // If cell is out of bounds
    if (row < 0 || col < 0|| row >= size || col >= size){

        return false; 
    }

    // If the cell is already visited
    if (vis[row][col]){

        return false;
    }

    // Otherwise, it can be visited

    return true;
}
void part1(int size,int point, int row, int col) {
    // Initialize a stack of pairs and
    // push the starting cell into it
    stack<pair<int, int> > st;
    st.push({ row, col });
    grid[row][col] = point;

    // Iterate until the
    // stack is not empty
    while (!st.empty()) {

        // Pop the top pair
        pair<int, int> curr = st.top();
        st.pop();
        int tRow = curr.first;
        int tCol = curr.second;

        // Check if the current cell is a valid cell or not
        if (!isValid(size, tRow, tCol))
        {
            continue;
        }
        //in our bool grid mark the cell to true so that we'll know if we were there before
        vis[tRow][tCol] = true;
        //if we are in the while loop that means we've found a link, so we increase it by 1
        linkCount++;


        // Check adjacent cells of the index   

        for (int i = 0; i < 4; i++) {
            int adjx = tRow + dRow[i];
            int adjy = tCol + dCol[i];

            if(isValid(size,adjx,adjy))
            {
                //if it's not out of bounds and equal to the point we search
                if(grid[adjx][adjy]==point)
                {
                    //Push that cell
                    st.push({ adjx, adjy });
                }
            }
        }
    }

    //after we have no any element in stack check the linkCount
    if (linkCount >= 3 ) //if it is >=3 then it means that it is ready to pop
    {

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++) 
            {
                if (vis[i][j]==true)//if we mark a cell to true, that means we were there before 
                {
                    vis[i][j] = false;//and we should make it false again
                    grid[i][j] = 0;//we'll set it value to 0 since it's popped
                    
                }
            }
        }
        //after the pop the point increases by one
        point++;
        //the first cell we've started with is know equal to our new point
        grid[row][col] = point;

        linkCount = 0;
        //we'll see if there is another pop after this one
        part1(size,point,row,col);
    }
    if (linkCount < 3)//if no pop just clear our bool grid, so they are not visited anymore
    {
        linkCount = 0;
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (vis[i][j] == true)
                {
                    vis[i][j] = false;

                }
            }
        }

    }
}
void part2(int size,  int row, int col) {
    //we find the value of the element bomb has been placed and set it as our bomb value
    int bombValue = grid2[row][col];
    //set the value of the grid that bomb has been placed to 0
    grid2[row][col] = 0;
    //we'll count our popped baloons
    int poppedCount = 1;
    
    //CHECK THE BOMB'S LEFT SIDE

    int adjx = row + dRow[0];
    int adjy = col + dCol[0];
    while(adjy>=0 && adjx>=0 && adjy<size && adjx<size ){//while not out of bounds 

        if(grid2[adjx][adjy]==bombValue){//if element in that index is equal to bombValue
            grid2[adjx][adjy] = 0;//we pop it by making it equal 0
            poppedCount++;//we increment the popped baloon count so that we can calculate the total point at the end of each iteration

        }
        adjx += dRow[0];
        adjy += dCol[0];

    }
    
    //CHECK THE BOMB'S RIGHT SIDE
    adjx = row + dRow[0];
    adjy = col + dCol[2];
    while (adjy >= 0 && adjx >= 0 && adjy < size && adjx < size) {
        if (grid2[adjx][adjy] == bombValue) {
            grid2[adjx][adjy] = 0; 
            poppedCount++; 

        }
        adjx += dRow[0];
        adjy += dCol[2];

    }
    //CHECK THE BOMB'S UPPER SIDE
    adjx = row + dRow[1];
    adjy = col + dCol[1];
    while (adjy >= 0 && adjx >= 0 && adjy < size && adjx < size) {
        if (grid2[adjx][adjy] == bombValue) {
            grid2[adjx][adjy] = 0;
            poppedCount++;

        }
        adjx += dRow[1];
        adjy += dCol[1];

    }
    //CHECK THE BOMB'S LOWER SIDE
    adjx = row + dRow[3];
    adjy = col + dCol[1];
    while (adjy >= 0 && adjx >= 0 && adjy < size && adjx < size) {
        if (grid2[adjx][adjy] == bombValue) {
            grid2[adjx][adjy] = 0;
            poppedCount++;

        }
        adjx += dRow[3];
        adjy += dCol[1];

    }
    //CHECK THE BOMB'S UPPER LEFT DIAG SIDE
    adjx = row + dRow[1];
    adjy = col + dCol[0];
    while (adjy >= 0 && adjx >= 0 && adjy < size && adjx < size) {
        if (grid2[adjx][adjy] == bombValue) {
            grid2[adjx][adjy] = 0;
            poppedCount++;

        }
        adjx += dRow[1];
        adjy += dCol[0];

    }
    //CHECK THE BOMB'S UPPER RIGHT DIAG SIDE
    adjx = row + dRow[1];
    adjy = col + dCol[2];
    while (adjy >= 0 && adjx >= 0 && adjy < size && adjx < size) {
        if (grid2[adjx][adjy] == bombValue) {
            grid2[adjx][adjy] = 0;
            poppedCount++;

        }
        adjx += dRow[1];
        adjy += dCol[2];

    }
    //CHECK THE BOMB'S LOWER LEFT DIAG SIDE
    adjx = row + dRow[3];
    adjy = col + dCol[0];
    while (adjy >= 0 && adjx >= 0 && adjy < size && adjx < size) {
        if (grid2[adjx][adjy] == bombValue) {
            grid2[adjx][adjy] = 0;
            poppedCount++;

        }
        adjx += dRow[3];
        adjy += dCol[0];
    }
    //CHECK THE BOMB'S LOWER RIGHT DIAG SIDE
    adjx = row + dRow[3];
    adjy = col + dCol[2];
    while (adjy >= 0 && adjx >= 0 && adjy < size && adjx < size) {
        if (grid2[adjx][adjy] == bombValue) {
            grid2[adjx][adjy] = 0;
            poppedCount++;

        }
        adjx += dRow[3];
        adjy += dCol[2];

    }
    totalPoint = totalPoint+(bombValue * poppedCount); //we multiply popped baloon count with the bombvalue and add it with total point

}
void lineReader(string inputFile,string outputFile) {
    int size;
    fstream inFile; //inputfile

    inFile.open(inputFile.c_str(), ios::in);//open the file
    if (inFile.is_open()) {        //if file succesfully opened
        string line;
        getline(inFile, line); //get the first line
        size = stoi(line);      //convert it to int and set it as 'size'
        if (fileIndex==0)
        {
            grid = new int* [size];    //we create an array of int pointers 
            for (int i = 0; i < size; i++) {//loop through
                grid[i] = new int[size];    //and create an int array for each pointer
                for (int j = 0; j < size; ++j) { //assign all elements to number 0
                    grid[i][j] = 0;
                }
            }


            vis = new bool* [size];    //we create an array of bool pointers to check if we visited a cell before while in part1
            memset(vis, false, sizeof vis);

            for (int i = 0; i < size; i++) {//loop through
                vis[i] = new bool[size];    //and create an int array for each pointer
                for (int j = 0; j < size; ++j) { //assign all elements to number false
                    vis[i][j] = false;

                }
            }


        }
        if (fileIndex == 1) {

            grid2 = new int* [size];    //we create an array of int pointers
            int value;
            
            for (int i = 0; i < size ;i++) {//loop through


                getline(inFile, line);

                // Vector of string to save tokens
                vector <string> tokens;

                // stringstream class check1
                stringstream check1(line);

                string intermediate;

                // Tokenizing w.r.t. space ' '
                while (getline(check1, intermediate, ' '))
                {
                    tokens.push_back(intermediate);
                }


                grid2[i] = new int[size+9];    //and create an int array for each pointer
                int colCount = 0;

                for (int j = 0; j < size; j++) {
                    //assign all elements to number 0
                    value= stoi(tokens[j]);

                    grid2[i][colCount] = value;
                    colCount++;
                }
                colCount = 0;
            }
           
        }
        int lineIndex = 0;
        int a = 0;

        while (getline(inFile, line)) {
            // Vector of string to save tokens
            vector <string> tokens;

            // stringstream class check1
            stringstream check1(line);

            string intermediate;

            // Tokenizing w.r.t. space ' '
            while (getline(check1, intermediate, ' '))
            {
                tokens.push_back(intermediate);
            }
            if (fileIndex == 0) { //if we are in input1
                int point, row, col;
                point = stoi(tokens[0]);     //since in ASCII integers start from 48, we can get the integer value of the char by simply substracting it with 48
                row = stoi(tokens[1]);
                col = stoi(tokens[2]);
                prow = row;
                pcol = col;
                part1(size, point, row, col);

            }


            if (fileIndex == 1) {//if we are in input2
                
                int point, row, col;
                row = stoi(tokens[0]);
                col = stoi(tokens[1]);
                prow = row;
                pcol = col;
                part2(size,row, col);

            }
            lineIndex++;
        }

        printGrid(size,outputFile);

        fileIndex++;
    }
    inFile.close();
    

}


int main(int argc, char** argv) {
    string inFile1 = "";
    string inFile2 = "";
    string outFile = "";
    if (argc == 4) {
        inFile1 = argv[1];
        inFile2 = argv[2];
        outFile = argv[3];
    }
    else {
        return 1;
    }
    lineReader(inFile1, outFile);
    lineReader(inFile2, outFile);


    
}
