import numpy as ny
import cv2

img = cv2.imread("GOT.jpg", 1)
cv2.imshow("image", img)
# ret,thresh1 = cv2.threshold(img,227,255,cv2.THRESH_BINARY)
# cv2.imshow("image1", thresh1)
# ret,thresh2 = cv2.threshold(img,100,255,cv2.THRESH_BINARY)
# cv2.imshow("image2", thresh2)
# ret,thresh3 = cv2.threshold(img,80,255,cv2.THRESH_BINARY)
# cv2.imshow("image3", thresh3)

blue, green, red = cv2.split(img)
cv2.imshow("BlueChannel", blue)
cv2.imshow("RedChannel", red)
cv2.imshow("GreenChannel", green)
k = cv2.waitKey(0)
if k == 27:  # Wait for the ESC key to exit
    cv2.destroyAllWindows()
elif k == ord("s"):  # Wait for the ‘S’ key to save and exit
    cv2.imwrite("GOT1.png", img)
    cv2.destroyAllWindows()
