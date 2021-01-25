import { Product } from "./product";
import { Size } from "./size";

export interface ProductSizeAssignment {

    productSizeAssignmentID: number
    quantity: number
    linkedProduct: Product
    linkedSize: Size
}
