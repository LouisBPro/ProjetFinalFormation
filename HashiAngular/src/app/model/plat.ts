import { Byte } from '@angular/compiler/src/util';

export class Plat {
  constructor(
    private _id?: number | undefined,
    private _nom?: string | undefined,
    private _description?: string | undefined,
    private _photo?: Byte[] | undefined,
    private _prix?: number | undefined
  ) {}

  /**
   * Getter prix
   * @return {number}
   */
  public get prix(): number | undefined {
    return this._prix;
  }

  /**
   * Setter prix
   * @param {number} value
   */
  public set prix(value: number | undefined) {
    this._prix = value;
  }

  /**
   * Getter photo
   * @return {Byte[]}
   */
  public get photo(): Byte[] | undefined {
    return this._photo;
  }

  /**
   * Setter photo
   * @param {Byte[]} value
   */
  public set photo(value: Byte[] | undefined) {
    this._photo = value;
  }

  /**
   * Getter description
   * @return {string}
   */
  public get description(): string | undefined {
    return this._description;
  }

  /**
   * Setter description
   * @param {string} value
   */
  public set description(value: string | undefined) {
    this._description = value;
  }

  /**
   * Getter nom
   * @return {string}
   */
  public get nom(): string | undefined {
    return this._nom;
  }

  /**
   * Setter nom
   * @param {string} value
   */
  public set nom(value: string | undefined) {
    this._nom = value;
  }

  /**
   * Getter id
   * @return {number}
   */
  public get id(): number | undefined {
    return this._id;
  }

  /**
   * Setter id
   * @param {number} value
   */
  public set id(value: number | undefined) {
    this._id = value;
  }
}
